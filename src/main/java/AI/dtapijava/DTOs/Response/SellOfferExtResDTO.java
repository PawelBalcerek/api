package AI.dtapijava.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellOfferExtResDTO {
    SellOfferResDTO sellOfferResDTO;
    private ExecDetailsResDTO execDetails;
}
