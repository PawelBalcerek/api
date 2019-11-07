package AI.dtapijava.Services;


import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.AddBuyOfferReqDTO;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Entities.BuyOffer;
import AI.dtapijava.Repositories.BuyOfferRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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

    public ExecTimeResDTO addBuyOffer(AddBuyOfferReqDTO addBuyOfferReqDTO) {
        return null;
    }

    public ExecTimeResDTO withdrawBuyOffer(int id) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        BuyOffer buyOffer = buyOfferRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        execHelper.addNewDbTime();
        buyOffer.setIsValid(false);
        execHelper.setStartDbTime(OffsetDateTime.now());
        buyOfferRepository.save(buyOffer);
        execHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(execHelper.getDbTime(),execHelper.getExecTime()));
    }
}
