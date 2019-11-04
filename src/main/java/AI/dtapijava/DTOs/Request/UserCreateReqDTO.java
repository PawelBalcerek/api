package AI.dtapijava.DTOs.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreateReqDTO {

    private String name;
    private String email;
    private String password;
}
