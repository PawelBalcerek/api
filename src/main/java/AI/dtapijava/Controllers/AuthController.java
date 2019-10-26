package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Request.AuthReqDTO;
import AI.dtapijava.DTOs.Response.AuthResDTO;
import AI.dtapijava.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {


    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<AuthResDTO> signin(@Valid @RequestBody AuthReqDTO authReqDTO){
          return ResponseEntity.ok(authService.getSigninCredential(authReqDTO));
    }





}
