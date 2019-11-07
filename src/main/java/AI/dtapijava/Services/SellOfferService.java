package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.AddSellOfferReqDTO;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Entities.SellOffer;
import AI.dtapijava.Repositories.SellOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
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

    public ExecTimeResDTO addSellOffer(AddSellOfferReqDTO addSellOfferReqDTO) {
        return null;
    }

    public ExecTimeResDTO withdrawSellOffer(int id) {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        SellOffer sellOffer = sellOfferRepository.findById(id).orElseThrow(() -> new RuntimeException("Sell offer not found"));
        execHelper.addNewDbTime();
        sellOffer.setIsValid(false);
        execHelper.setStartDbTime(OffsetDateTime.now());
        sellOfferRepository.save(sellOffer);
        execHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(execHelper.getDbTime(),execHelper.getExecTime()));
    }
}
