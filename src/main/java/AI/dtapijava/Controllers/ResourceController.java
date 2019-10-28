package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Response.ResourceResDTO;
import AI.dtapijava.Services.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/resource/user/{userId}")
    public ResponseEntity<List<ResourceResDTO>> getResourcesToUser(@PathVariable int userId) {
        return ResponseEntity.ok(resourceService.getResourcesToUser(userId));
    }

    @GetMapping("/resource/company/{companyId}")
    public ResponseEntity<List<ResourceResDTO>> getResourcesToCompany(@PathVariable int companyId) {
        return ResponseEntity.ok(resourceService.getResourcesToCompany(companyId));
    }
}
