package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.BuyOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserBuyOfferResDTO {
    private long id;
    private ResourceCompanyResDTO company;
    private int amount;
    private int startAmount;
    private double price;
    private OffsetDateTime date;
    private Boolean isValid;

    public UserBuyOfferResDTO(BuyOffer buyOffer) {
        id = buyOffer.getID();
        company = new ResourceCompanyResDTO(buyOffer.getResource().getCompany());
        amount = buyOffer.getAmount();
        startAmount = buyOffer.getStartAmount();
        price = buyOffer.getMaxPrice();
        date = buyOffer.getDate();
        isValid = buyOffer.getIsValid();
    }
}
