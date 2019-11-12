package AI.dtapijava.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyOffersResDTO {
    private List<BuyOfferResDTO> buyOffers;
    private ExecDetailsResDTO execDetails;
}
