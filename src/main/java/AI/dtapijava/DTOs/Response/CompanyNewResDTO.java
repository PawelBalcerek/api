package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class CompanyNewResDTO {
    private ResourceCompanyResDTO company;
    private Double indexPrice;

    public CompanyNewResDTO(Company company, Double indexPrice) {
        this.company = new ResourceCompanyResDTO(company);
        this.indexPrice = indexPrice;
    }
}
