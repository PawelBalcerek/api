package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Response.UserResDTO;
import AI.dtapijava.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResDTO> getUser(@PathVariable int id){
        return ResponseEntity.ok(new UserResDTO(userService.getUser(id)));

    }
    @GetMapping("/users")
    public ResponseEntity<List<UserResDTO>> getUsers(){
        return ResponseEntity.ok(userService.getUsers().stream().map(UserResDTO::new).collect(Collectors.toList()));
    }

}
