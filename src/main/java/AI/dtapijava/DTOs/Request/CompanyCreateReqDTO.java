package AI.dtapijava.DTOs.Request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreateReqDTO {
    private String name;
    private int resourcesAmount;
    private int userId;
}
