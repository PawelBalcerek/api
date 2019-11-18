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
public class UserResDTO {

    private Integer id;
    private String name;
    private String email;
    private Double cash;

    public UserResDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.cash = user.getCash();
    }

}
