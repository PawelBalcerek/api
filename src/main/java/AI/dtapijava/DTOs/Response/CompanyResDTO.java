package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CompanyResDTO {
    private String name;
    private Integer id;
    private Double indexPrice;


    public CompanyResDTO(Company comp) {
        this.name = comp.getName();
        this.id = comp.getID();
//        this.indexPrice = comp.getResources().stream().map(Resource::getAmount)

    }
}
