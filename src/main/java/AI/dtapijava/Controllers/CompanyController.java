package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.DTOs.Response.CompanyResDTO;
import AI.dtapijava.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<?> createCompany(@Valid @RequestBody CompanyCreateReqDTO companyCreateReqDTO) {
        try {
            companyService.createCompany(companyCreateReqDTO);
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResDTO> getCompany(@PathVariable int id) {
        return ResponseEntity.ok(new CompanyResDTO(companyService.getCompany(id)));
    }

    @GetMapping("/companies")
    public ResponseEntity<List<CompanyResDTO>> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies().stream().map(CompanyResDTO::new).collect(Collectors.toList()));
    }
}
