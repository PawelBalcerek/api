package AI.dtapijava.Controllers;


import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello World!");
    }

}
