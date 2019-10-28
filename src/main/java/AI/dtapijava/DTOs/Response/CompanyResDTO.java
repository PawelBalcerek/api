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
    private List<ResourceResDTO> companyResources;
    private List<Integer> resourceIds;
    private List<UserResDTO> userResDTO;

    public CompanyResDTO(Company comp) {
        this.name = comp.getName();
        this.resourceIds = comp.getResources().stream().map(Resource::getID).collect(Collectors.toList());
        this.userResDTO = comp.getResources().stream().map(Resource::getUser).map(UserResDTO::new).collect(Collectors.toList());
        companyResources = comp.getResources().stream().map(ResourceResDTO::new).collect(Collectors.toList());
    }
}
