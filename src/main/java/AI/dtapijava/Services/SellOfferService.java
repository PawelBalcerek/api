package AI.dtapijava.Services;

import AI.dtapijava.Entities.SellOffer;
import AI.dtapijava.Repositories.SellOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellOfferService {
    @Autowired
    private SellOfferRepository sellOfferRepository;

    public SellOffer getSellOffer (int id) {
        return sellOfferRepository.getOne(id);
    }

    public List<SellOffer> getSellOffers() {
        return sellOfferRepository.findAll();
    }

    public List<SellOffer> getSellOffersValid (Boolean valid) {
        return sellOfferRepository.findByIsValid(valid);
    }
}
