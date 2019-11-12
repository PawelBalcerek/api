package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserResourceResDTO {
    long id;
    ResourceCompanyResDTO company;
    int amount;

    public UserResourceResDTO(Resource resource) {
        id = resource.getID();
        amount=resource.getAmount();
        company = new ResourceCompanyResDTO(resource.getCompany());
    }
}
