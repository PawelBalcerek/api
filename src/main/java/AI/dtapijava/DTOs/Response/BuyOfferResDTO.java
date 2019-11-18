package AI.dtapijava.DTOs.Response;


import AI.dtapijava.Entities.BuyOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyOfferResDTO {
    private int id;
    private Integer amount;
    private Double maxPrice;
    private OffsetDateTime date;
    private Boolean isValid;
    private Integer startAmount;

    public BuyOfferResDTO(BuyOffer buyOffer) {
        this.id = buyOffer.getID();
        this.amount = buyOffer.getAmount();
        this.maxPrice = buyOffer.getMaxPrice();
        this.date = buyOffer.getDate();
        this.isValid = buyOffer.getIsValid();
        this.startAmount = buyOffer.getStartAmount();

    }
}
