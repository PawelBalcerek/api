package AI.dtapijava.Services;


import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.UserCreateReqDTO;
import AI.dtapijava.DTOs.Response.*;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Exceptions.UserNotFoundExceptions;
import AI.dtapijava.Infrastructure.Util.UserUtils;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.ResourceRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CompanyRepository companyRepository;

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

    public UserFullResDTO getActiveUser(){
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(()->new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        return new UserFullResDTO(user,execHelper.getDbTime(),execHelper.getExecTime());
    }

    public ExecTimeResDTO createUser(UserCreateReqDTO userCreateReqDTO){
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        User user = User.builder()
                .email(userCreateReqDTO.getEmail())
                .name(userCreateReqDTO.getName())
                .password(passwordEncoder.encode(userCreateReqDTO.getPassword()))
                .cash(0.0)
                .build();

        execHelper.setStartDbTime(OffsetDateTime.now());
        if(userRepository.existsByNameOrEmail(userCreateReqDTO.getName(),userCreateReqDTO.getEmail())){
            System.out.println("Rzuc wyjątek - użytkownik istnieje w azie ");
        }

        User newUser = userRepository.save(user);
        execHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(execHelper.getDbTime(),execHelper.getExecTime()));
    }

    public UserResourcesResDTO getActiveUserResources() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        User user = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(()->new UserNotFoundExceptions("User not found!"));
        execHelper.addNewDbTime();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<Resource> userResources = resourceRepository.getAllResourcesFromUser(user.getId());
        execHelper.addNewDbTime();

        List<UserResourceResDTO> resourcesList = userResources.stream().map(UserResourceResDTO::new).collect(Collectors.toList());

        return new UserResourcesResDTO(resourcesList, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }
}
