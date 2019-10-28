package AI.dtapijava.Services;


import AI.dtapijava.Entities.BuyOffer;
import AI.dtapijava.Repositories.BuyOfferRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyOfferService {
    @Autowired
    private BuyOfferRepository buyOfferRepository;

    public BuyOffer getBuyOffer (int id) {
        return buyOfferRepository.getOne(id);
    }

    public List<BuyOffer> getBuyOffers() {
        return buyOfferRepository.findAll();
    }

    public List<BuyOffer> getBuyOffersValid (Boolean valid) {
        return buyOfferRepository.findByIsValid(valid);
    }

}
