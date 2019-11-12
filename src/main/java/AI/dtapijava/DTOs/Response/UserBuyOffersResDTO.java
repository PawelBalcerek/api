package AI.dtapijava.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserBuyOffersResDTO {
    private List<UserBuyOfferResDTO> buyOffers;
    private ExecDetailsResDTO execDetails;
}
