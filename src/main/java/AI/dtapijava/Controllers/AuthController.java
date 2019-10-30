package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Request.AuthReqDTO;
import AI.dtapijava.DTOs.Response.AuthResDTO;
import AI.dtapijava.Infrastructure.Util.UserUtils;
import AI.dtapijava.Services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {

    private final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResDTO> signin(@Valid @RequestBody AuthReqDTO authReqDTO){
          return ResponseEntity.ok(authService.getSigninCredential(authReqDTO));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(){
        log.debug("User by id: {} logout", UserUtils.getCurrentUserId());
        return ResponseEntity.ok().build();
    }







}
