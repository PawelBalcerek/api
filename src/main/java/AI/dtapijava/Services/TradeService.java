package AI.dtapijava.Services;


import AI.dtapijava.Entities.*;
import AI.dtapijava.Repositories.*;
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
            if (companySemaphore == null) {
                companySemaphore = new Semaphore(1);
                companiesLocks.put(companyId, companySemaphore);
            }
        }
        try {
            companySemaphore.acquire();
            Configuration tableSizeConf = configurationRepository.findById("tableSize").orElse(new Configuration("tableSize", 5));
            Pageable page = PageRequest.of(0, tableSizeConf.getNumber());
            while (true) {
                List<SellOffer> sellOffers = sellOfferRepository.getAllPendingSellOffersForCompanyId(companyId, page);
                List<BuyOffer> buyOffers = buyOfferRepository.getAllPendingBuyOffersForCompanyId(companyId, page);
                if (sellOffers.size() != tableSizeConf.getNumber() || buyOffers.size() != tableSizeConf.getNumber()) {
                    return;
                }
                int indexSell = 0;
                int indexBuy = 0;
                while (true) {
                    if (indexBuy >= buyOffers.size() || indexSell >= sellOffers.size()) break;
                    if (buyOffers.get(indexBuy).getMaxPrice() >= sellOffers.get(indexSell).getPrice()) {
                        int amountToSell = sellOffers.get(indexSell).getAmount();
                        int amountToBuy = buyOffers.get(indexBuy).getAmount();
                        if (amountToBuy > amountToSell) {
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
                            updateResourceforSellOfferId(sellOffers.get(indexSell).getResource().getID(), transactionAmount, transaction.getPrice());
                            updateResourceforBuyOfferId(buyOffers.get(indexBuy).getResource().getID(), transactionAmount, transaction.getPrice(), buyOffers.get(indexBuy).getMaxPrice());
                            sellOfferRepository.save(sellOffers.get(indexSell));
                            buyOfferRepository.save(buyOffers.get(indexBuy));
                            transactionRepository.save(transaction);
                            ++indexSell;
                        } else if (amountToBuy < amountToSell) {
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
                            updateResourceforSellOfferId(sellOffers.get(indexSell).getResource().getID(), transactionAmount, transaction.getPrice());
                            updateResourceforBuyOfferId(buyOffers.get(indexBuy).getResource().getID(), transactionAmount, transaction.getPrice(), buyOffers.get(indexBuy).getMaxPrice());
                            sellOfferRepository.save(sellOffers.get(indexSell));
                            buyOfferRepository.save(buyOffers.get(indexBuy));
                            transactionRepository.save(transaction);
                            ++indexBuy;
                        } else {
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
                            updateResourceforSellOfferId(sellOffers.get(indexSell).getResource().getID(), transactionAmount, transaction.getPrice());
                            updateResourceforBuyOfferId(buyOffers.get(indexBuy).getResource().getID(), transactionAmount, transaction.getPrice(), buyOffers.get(indexBuy).getMaxPrice());
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
            resource.setUser(user);
            resourceRepository.save(resource);
        }
    }
}
