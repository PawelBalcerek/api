package AI.dtapijava.DTOs.Response;


import AI.dtapijava.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserFullResDTO {

    private UserResDTO user;
    private ExecDetailsResDTO execDetails;

    public UserFullResDTO(User user, Integer dbTime, Integer execTime){
        this.user = new UserResDTO(user);
        this.execDetails = new ExecDetailsResDTO(dbTime,execTime);
    }

}
