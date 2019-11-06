package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Services.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class DatabaseController {
    @Autowired
    DatabaseService databaseService;

    @DeleteMapping("/database/clean")
    public ResponseEntity<ExecTimeResDTO> cleanDatabase() {
        return ResponseEntity.ok(databaseService.cleanDatabase());
    }
}
