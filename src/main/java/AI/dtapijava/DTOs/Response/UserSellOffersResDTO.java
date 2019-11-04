package AI.dtapijava.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserSellOffersResDTO {
    private List<UserSellOfferResDTO> sellOffers;
    private ExecDetailsResDTO execDetails;
}
