package AI.dtapijava.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserResourcesResDTO {
    List<UserResourceResDTO> resources;
    private ExecDetailsResDTO execDetails;

}
