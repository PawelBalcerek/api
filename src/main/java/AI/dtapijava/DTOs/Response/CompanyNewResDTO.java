package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Company;
import lombok.Getter;

@Getter
public class CompanyNewResDTO {
    //private ResourceCompanyResDTO company;
    private Integer id;
    private String name;
    private Double indexPrice;

    public CompanyNewResDTO(Company company, Double indexPrice) {
        this.id = company.getID();
        this.name = company.getName();
        //this.company = new ResourceCompanyResDTO(company);
        this.indexPrice = indexPrice;
    }

    public CompanyNewResDTO(Company company) {
        this.id = company.getID();
        this.name = company.getName();
        //this.company = new ResourceCompanyResDTO(company);
        this.indexPrice = null;
    }
}
