package AI.dtapijava.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddBuyOfferReqDTO {
    private Integer companyId;
    private Integer amount;
    private Double price;
}
