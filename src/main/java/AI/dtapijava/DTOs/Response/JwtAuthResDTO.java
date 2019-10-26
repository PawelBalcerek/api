package AI.dtapijava.DTOs.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResDTO {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthResDTO(String accessToken) {
        this.accessToken = accessToken;
    }
}
