package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResourceResDTO {

    private UserResDTO userDTO;
    //private CompanyResDTO companyDTO;
    private Integer amount;

    public ResourceResDTO(Resource resource){
        this.userDTO = new UserResDTO(resource.getUser());
        this.companyDTO = new CompanyResDTO(resource.getCompany());
        this.amount = resource.getAmount();
    }

}
