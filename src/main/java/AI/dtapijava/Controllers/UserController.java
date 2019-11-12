package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Request.AuthReqDTO;
import AI.dtapijava.DTOs.Request.UserCreateReqDTO;
import AI.dtapijava.DTOs.Response.*;
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
    public ResponseEntity<UserFullResDTO> getActiveUser(){
        return ResponseEntity.ok(userService.getActiveUser());
    }

    @PostMapping("/users")
    public ResponseEntity<ExecTimeResDTO> createUser(@RequestBody UserCreateReqDTO userCreateReqDTO){
        return ResponseEntity.ok(userService.createUser(userCreateReqDTO));
    }

    @GetMapping("/users/resources")
    public ResponseEntity<UserResourcesResDTO> getActiveUserResources() {
        return ResponseEntity.ok(userService.getActiveUserResources());
    }

    @GetMapping("/users/sell-offers")
    public ResponseEntity<UserSellOffersResDTO> getActiveUserSellOffers() {
        return ResponseEntity.ok(userService.getActiveUserSellOffers());
    }

    @GetMapping("/users/buy-offers")
    public ResponseEntity<UserBuyOffersResDTO> getActiveUserBuyOffers() {
        return ResponseEntity.ok(userService.getActiveUserBuyOffers());
    }

    @GetMapping("/users/transactions")
    public ResponseEntity<UserTransactionsResDTO> getActiveUserTransactions() {
        return ResponseEntity.ok(userService.getActiveUserTransactions());
    }

    @GetMapping("/users/transactions/sell")
    public ResponseEntity<UserTransactionsResDTO> getActiveUserSellTransactions() {
        return ResponseEntity.ok(userService.getActiveUserSellTransactions());
    }

    @GetMapping("/users/transactions/buy")
    public ResponseEntity<UserTransactionsResDTO> getActiveUserBuyTransactions() {
        return ResponseEntity.ok(userService.getActiveUserBuyTransactions());
    }
}
