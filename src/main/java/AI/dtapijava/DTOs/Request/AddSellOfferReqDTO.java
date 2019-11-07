package AI.dtapijava.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddSellOfferReqDTO {
    private Integer resourceId;
    private Integer amount;
    private Double price;
}
