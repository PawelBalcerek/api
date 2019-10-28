package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Company;

import java.util.stream.Collectors;

public class CompanyResDTO {
    private String name;
    private ResourceResDTO companyResources;

    public CompanyResDTO(Company comp) {
        this.name = comp.getName();
        companyResources = comp.getResources().stream().map(ResourceResDTO::new).collect(Collectors.toList());
    }
}
