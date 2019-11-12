package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.SellOffer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellOfferResDTO {
    private int id;
    private Integer amount;
    private Double price;
    private OffsetDateTime date;
    private Boolean isValid;
    private Integer startAmount;

    public SellOfferResDTO (SellOffer sellOffer) {
        this.amount = sellOffer.getAmount();
        this.price = sellOffer.getPrice();
        this.date = sellOffer.getDate();
        this.isValid = sellOffer.getIsValid();
        this.startAmount = sellOffer.getStartAmount();

    }
}
