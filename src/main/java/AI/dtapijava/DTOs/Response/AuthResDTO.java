package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthResDTO {
    //    private Integer id;
//    private String name;
//    private String email;
    private String jwt;
    private ExecDetailsResDTO execDetails;


//    public AuthResDTO(User user){
//        this.id = user.getId();
//        this.name = user.getName();
//        this.email = user.getEmail();
//    }

    public AuthResDTO(JwtAuthResDTO jwtAuthRes, User user) {
        //this(user);
        this.jwt = jwtAuthRes.getAccessToken();
    }

    public AuthResDTO(JwtAuthResDTO jwtAuthRes, User user, Integer dbTime, Integer execTime) {
        //this(user);
        this.jwt = jwtAuthRes.getAccessToken();
        this.execDetails = new ExecDetailsResDTO(dbTime, execTime);
    }

}
