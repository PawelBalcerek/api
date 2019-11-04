package AI.dtapijava.Services;


import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Response.UsersFullResDTO;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Exceptions.UserNotFoundExceptions;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(int id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new UserNotFoundExceptions("User not found!"));
    }

    public UsersFullResDTO getUsers() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<User> users = userRepository.findAll();
        execHelper.addNewDbTime(OffsetDateTime.now());

        return new UsersFullResDTO(users,execHelper.getDbTime(),execHelper.getExecTime());
    }
}
