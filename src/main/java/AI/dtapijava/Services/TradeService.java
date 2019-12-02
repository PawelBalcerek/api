package AI.dtapijava.Services;


import AI.dtapijava.Entities.*;
import AI.dtapijava.Repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Service

public class TradeService {
    private final Logger log = LoggerFactory.getLogger(TradeService.class);
    private static AtomicBoolean flag = new AtomicBoolean(true);
    private static final HashMap<Integer, Semaphore> companiesLocks = new HashMap<>();
    @Autowired
    private SellOfferRepository sellOfferRepository;
    @Autowired
    private BuyOfferRepository buyOfferRepository;
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private UserRepository userRepository;

    private void startInnerThread(int companyId) {
        Semaphore companySemaphore = null;
        synchronized (companiesLocks) {
            companySemaphore = companiesLocks.getOrDefault(companyId, null);
            log.debug("Trying to get proper semaphore for company: " + companyId);
            if (companySemaphore == null) {
                log.debug("Semaphore not found, creating new one: " + companyId);
                companySemaphore = new Semaphore(1);
                companiesLocks.put(companyId, companySemaphore);
            }
        }
        try {
            log.debug("Trying to acquire semaphore, company: " + companyId);
            companySemaphore.acquire();
            log.debug("Acquired semaphore, company: " + companyId);
            Configuration tableSizeConf = configurationRepository.findById("tableSize").orElse(new Configuration("tableSize", 5));
            Pageable page = PageRequest.of(0, tableSizeConf.getNumber());
            while (true) {
                List<SellOffer> sellOffers = sellOfferRepository.getAllPendingSellOffersForCompanyId(companyId, page);
                List<BuyOffer> buyOffers = buyOfferRepository.getAllPendingBuyOffersForCompanyId(companyId, page);
                log.debug("Company: " + companyId + " sellOffers.size=" + sellOffers.size() + " buyOffers.size=" + buyOffers.size());
                if (sellOffers.size() != tableSizeConf.getNumber() || buyOffers.size() != tableSizeConf.getNumber()) {
                    return;
                }
                int indexSell = 0;
                int indexBuy = 0;
                while (true) {
                    if (indexBuy >= buyOffers.size() || indexSell >= sellOffers.size()) break;
                    if (buyOffers.get(indexBuy).getMaxPrice() >= sellOffers.get(indexSell).getPrice()) {
                        log.debug("prices BUY>=SELL, company: " + companyId);
                        int amountToSell = sellOffers.get(indexSell).getAmount();
                        int amountToBuy = buyOffers.get(indexBuy).getAmount();
                        if (amountToBuy > amountToSell) {
                            log.debug("amount BUY>SELL, company: " + companyId);
                            int transactionAmount = amountToSell;
                            Transaction transaction = Transaction.builder()
                                    .amount(transactionAmount)
                                    .buyOffer(buyOffers.get(indexBuy))
                                    .sellOffer(sellOffers.get(indexSell))
                                    .date(OffsetDateTime.now())
                                    .price(sellOffers.get(indexSell).getPrice())
                                    .build();
                            sellOffers.get(indexSell).setAmount(sellOffers.get(indexSell).getAmount() - transactionAmount);
                            buyOffers.get(indexBuy).setAmount(buyOffers.get(indexBuy).getAmount() - transactionAmount);
                            log.debug("Before critical section, current company: " + companyId);
                            updateResourceforSellOfferId(sellOffers.get(indexSell).getResource().getID(), transactionAmount, transaction.getPrice());
                            updateResourceforBuyOfferId(buyOffers.get(indexBuy).getResource().getID(), transactionAmount, transaction.getPrice(), buyOffers.get(indexBuy).getMaxPrice());
                            log.debug("After critical section, current company: " + companyId);sellOfferRepository.save(sellOffers.get(indexSell));
                            sellOfferRepository.save(sellOffers.get(indexSell));
                            buyOfferRepository.save(buyOffers.get(indexBuy));
                            transactionRepository.save(transaction);
                            ++indexSell;
                        } else if (amountToBuy < amountToSell) {
                            log.debug("amount BUY<SELL, company: " + companyId);
                            int transactionAmount = amountToBuy;
                            Transaction transaction = Transaction.builder()
                                    .amount(transactionAmount)
                                    .buyOffer(buyOffers.get(indexBuy))
                                    .sellOffer(sellOffers.get(indexSell))
                                    .date(OffsetDateTime.now())
                                    .price(sellOffers.get(indexSell).getPrice())
                                    .build();
                            sellOffers.get(indexSell).setAmount(sellOffers.get(indexSell).getAmount() - transactionAmount);
                            buyOffers.get(indexBuy).setAmount(buyOffers.get(indexBuy).getAmount() - transactionAmount);
                            log.debug("Before critical section, current company: " + companyId);
                            updateResourceforSellOfferId(sellOffers.get(indexSell).getResource().getID(), transactionAmount, transaction.getPrice());
                            updateResourceforBuyOfferId(buyOffers.get(indexBuy).getResource().getID(), transactionAmount, transaction.getPrice(), buyOffers.get(indexBuy).getMaxPrice());
                            log.debug("After critical section, current company: " + companyId);sellOfferRepository.save(sellOffers.get(indexSell));
                            sellOfferRepository.save(sellOffers.get(indexSell));
                            buyOfferRepository.save(buyOffers.get(indexBuy));
                            transactionRepository.save(transaction);
                            ++indexBuy;
                        } else {
                            log.debug("amount BUY==SELL, company: " + companyId);
                            int transactionAmount = amountToBuy;
                            Transaction transaction = Transaction.builder()
                                    .amount(transactionAmount)
                                    .buyOffer(buyOffers.get(indexBuy))
                                    .sellOffer(sellOffers.get(indexSell))
                                    .date(OffsetDateTime.now())
                                    .price(sellOffers.get(indexSell).getPrice())
                                    .build();
                            sellOffers.get(indexSell).setAmount(sellOffers.get(indexSell).getAmount() - transactionAmount);
                            buyOffers.get(indexBuy).setAmount(buyOffers.get(indexBuy).getAmount() - transactionAmount);
                            log.debug("Before critical section, current company: " + companyId);
                            updateResourceforSellOfferId(sellOffers.get(indexSell).getResource().getID(), transactionAmount, transaction.getPrice());
                            updateResourceforBuyOfferId(buyOffers.get(indexBuy).getResource().getID(), transactionAmount, transaction.getPrice(), buyOffers.get(indexBuy).getMaxPrice());
                            log.debug("After critical section, current company: " + companyId);
                            sellOfferRepository.save(sellOffers.get(indexSell));
                            buyOfferRepository.save(buyOffers.get(indexBuy));
                            transactionRepository.save(transaction);
                            ++indexBuy;
                            ++indexSell;
                        }
                    } else break;
                }
            }
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            companySemaphore.release();
        }
    }

    public void startThread(final int companyId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                startInnerThread(companyId);
            }
        }).start();
    }

    private void updateResourceforSellOfferId(int resourceId, int amount, double cash) {
        synchronized (companiesLocks) {
            Resource resource = resourceRepository.getOne(resourceId);
            User user = resource.getUser();
            user.setCash(user.getCash() + amount * cash);
            resource.setUser(user);
            resourceRepository.save(resource);
        }
    }

    private void updateResourceforBuyOfferId(int resourceId, int amount, double transactionPrice, double buyOfferPrice) {
        synchronized (companiesLocks) {
            Resource resource = resourceRepository.getOne(resourceId);
            User user = resource.getUser();
            user.setCash(user.getCash() + amount * (buyOfferPrice - transactionPrice));
            resource.setAmount(resource.getAmount()+amount);
            resource.setUser(user);
            resourceRepository.save(resource);
        }
    }
}
