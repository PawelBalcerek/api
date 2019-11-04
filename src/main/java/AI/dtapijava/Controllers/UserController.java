package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Request.AuthReqDTO;
import AI.dtapijava.DTOs.Request.UserCreateReqDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.DTOs.Response.UserResDTO;
import AI.dtapijava.DTOs.Response.UsersFullResDTO;
import AI.dtapijava.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/users/all")
    public ResponseEntity<UsersFullResDTO> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/users")
    public ResponseEntity<UsersFullResDTO> getActiveUser(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<ExecTimeResDTO> createUser(@RequestBody UserCreateReqDTO userCreateReqDTO){
        return ResponseEntity.ok(userService.createUser(userCreateReqDTO));
    }
}
