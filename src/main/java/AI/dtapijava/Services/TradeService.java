package AI.dtapijava.Services;


import AI.dtapijava.Entities.BuyOffer;
import AI.dtapijava.Entities.Configuration;
import AI.dtapijava.Entities.SellOffer;
import AI.dtapijava.Entities.Transaction;
import AI.dtapijava.Repositories.BuyOfferRepository;
import AI.dtapijava.Repositories.ConfigurationRepository;
import AI.dtapijava.Repositories.SellOfferRepository;
import AI.dtapijava.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service

public class TradeService {
    private static AtomicBoolean flag = new AtomicBoolean(true);
    @Autowired
    private SellOfferRepository sellOfferRepository;
    @Autowired
    private BuyOfferRepository buyOfferRepository;
    @Autowired
    private ConfigurationRepository configurationRepository;
    @Autowired
    private TransactionRepository transactionRepository;

    private synchronized void startInnerThread(int companyId) {
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
                if (indexBuy > buyOffers.size() || indexSell > sellOffers.size()) break;
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
                        sellOffers.get(indexSell).setAmount(sellOffers.get(indexSell).getAmount()-transactionAmount);
                        buyOffers.get(indexBuy).setAmount(buyOffers.get(indexBuy).getAmount()-transactionAmount);
                        transactionRepository.save(transaction);
                        indexSell++;
                    }
                    else if (amountToBuy < amountToSell) {
                        int transactionAmount = amountToBuy;
                        Transaction transaction = Transaction.builder()
                                .amount(transactionAmount)
                                .buyOffer(buyOffers.get(indexBuy))
                                .sellOffer(sellOffers.get(indexSell))
                                .date(OffsetDateTime.now())
                                .price(sellOffers.get(indexSell).getPrice())
                                .build();
                        sellOffers.get(indexSell).setAmount(sellOffers.get(indexSell).getAmount()-transactionAmount);
                        buyOffers.get(indexBuy).setAmount(buyOffers.get(indexBuy).getAmount()-transactionAmount);
                        transactionRepository.save(transaction);
                        indexBuy++;
                    }
                    else {
                        int transactionAmount = amountToBuy;
                        Transaction transaction = Transaction.builder()
                                .amount(transactionAmount)
                                .buyOffer(buyOffers.get(indexBuy))
                                .sellOffer(sellOffers.get(indexSell))
                                .date(OffsetDateTime.now())
                                .price(sellOffers.get(indexSell).getPrice())
                                .build();
                        sellOffers.get(indexSell).setAmount(sellOffers.get(indexSell).getAmount()-transactionAmount);
                        buyOffers.get(indexBuy).setAmount(buyOffers.get(indexBuy).getAmount()-transactionAmount);
                        transactionRepository.save(transaction);
                        indexBuy++;
                        indexSell++;
                    }
                }
                else break;
            }
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
}
