package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResDTO {
    private Integer id;
    private String name;
    private String email;
    private String accessToken;
    private String tokenType;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;

    public AuthResDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();

    }

    public AuthResDTO(JwtAuthResDTO jwtAuthRes, User user) {
        this(user);
        this.accessToken = jwtAuthRes.getAccessToken();
        this.tokenType = jwtAuthRes.getTokenType();
    }

    public AuthResDTO(JwtAuthResDTO jwtAuthResDTO, User user, OffsetDateTime startTime,OffsetDateTime endTime){
        this(user);
        this.accessToken = jwtAuthResDTO.getAccessToken();
        this.tokenType = jwtAuthResDTO.getTokenType();
        this.startTime = startTime;
        this.endTime = endTime;
    }



}
