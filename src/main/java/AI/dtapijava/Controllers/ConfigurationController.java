package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Request.ConfigurationReqDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PutMapping("/configurations")
    public ResponseEntity<ExecTimeResDTO> setConfiguration(@RequestBody ConfigurationReqDTO configurationReqDTO) {
        return ResponseEntity.ok(configurationService.setConfiguration(configurationReqDTO));
    }

}
