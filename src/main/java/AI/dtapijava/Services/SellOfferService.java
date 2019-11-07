package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.AddSellOfferReqDTO;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.SellOffer;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Exceptions.UserNotFoundExceptions;
import AI.dtapijava.Infrastructure.Util.UserUtils;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.ResourceRepository;
import AI.dtapijava.Repositories.SellOfferRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SellOfferService {
    @Autowired
    private SellOfferRepository sellOfferRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    public SellOffer getSellOffer (int id) {
        return sellOfferRepository.getOne(id);
    }

    public List<SellOffer> getSellOffers() {
        return sellOfferRepository.findAll();
    }

    public List<SellOffer> getSellOffersValid (Boolean valid) {
        return sellOfferRepository.findByIsValid(valid);
    }

    public ExecTimeResDTO addSellOffer(AddSellOfferReqDTO addSellOfferReqDTO) {ExecDetailsHelper execHelper = new ExecDetailsHelper();
        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(()->new UserNotFoundExceptions("User not found!"));
        Resource resource = resourceRepository.findById(addSellOfferReqDTO.getResourceId()).orElseThrow(() -> new RuntimeException("Resource not found"));
        execHelper.addNewDbTime();

        if (resource.getAmount() < addSellOfferReqDTO.getAmount()) {
            throw new RuntimeException("Not enough actions to sell");
        }

        SellOffer sellOffer = SellOffer.builder()
                .resource(resource)
                .isValid(true)
                .amount(addSellOfferReqDTO.getAmount())
                .startAmount(addSellOfferReqDTO.getAmount())
                .date(OffsetDateTime.now())
                .build();
        execHelper.setStartDbTime(OffsetDateTime.now());
        sellOfferRepository.save(sellOffer);
        execHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(execHelper.getDbTime(),execHelper.getExecTime()));
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
