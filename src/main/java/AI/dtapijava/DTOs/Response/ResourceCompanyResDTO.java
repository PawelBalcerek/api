package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ResourceCompanyResDTO {
    long id;
    String name;

    public ResourceCompanyResDTO(Company company) {
        id = company.getID();
        name = company.getName();
    }
}
