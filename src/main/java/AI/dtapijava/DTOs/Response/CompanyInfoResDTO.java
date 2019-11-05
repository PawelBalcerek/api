package AI.dtapijava.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class CompanyInfoResDTO {
    public List<CompanyNewResDTO> companies;
    private ExecDetailsResDTO execDetails;
}
