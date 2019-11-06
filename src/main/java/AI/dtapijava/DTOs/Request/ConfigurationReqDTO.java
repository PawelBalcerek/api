package AI.dtapijava.DTOs.Request;

import AI.dtapijava.Enums.ConfigType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConfigurationReqDTO {
    private String name;
    private Integer value;
}
