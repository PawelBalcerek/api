package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.DTOs.Response.CompanyInfoResDTO;
import AI.dtapijava.DTOs.Response.CompanyResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/companies")
    public ResponseEntity<ExecTimeResDTO> createCompany(@Valid @RequestBody CompanyCreateReqDTO companyCreateReqDTO) {
        ;
        return ResponseEntity.ok(companyService.createCompany(companyCreateReqDTO));
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResDTO> getCompany(@PathVariable int id) {
        return ResponseEntity.ok(new CompanyResDTO(companyService.getCompany(id)));
    }

    @GetMapping("/companies")
    public ResponseEntity<CompanyInfoResDTO> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }
}
