package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class UsersFullResDTO {
    private List<UserResDTO> user;
    private ExecDetailsResDTO execDetails;

    public UsersFullResDTO(List<User> users, ExecDetailsHelper execDetailsHelper){
        this.user = users.stream().map(UserResDTO::new).collect(Collectors.toList());
        this.execDetails = new ExecDetailsResDTO(execDetailsHelper.getDbTime(),execDetailsHelper.getExecTime());
    }

    public UsersFullResDTO(List<User> users, Integer dbTime,Integer execTime){
        this.user = users.stream().map(UserResDTO::new).collect(Collectors.toList());
        this.execDetails = new ExecDetailsResDTO(dbTime,execTime);
    }


}
