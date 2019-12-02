package AI.dtapijava.Controllers;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.DTOs.Response.CompanyInfoResDTO;
import AI.dtapijava.DTOs.Response.CompanyResDTO;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Services.CompanyService;
import AI.dtapijava.Services.TradeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class CompanyController {
    private final Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompanyService companyService;
    @Autowired
    private TradeService tradeService;

    @PostMapping("/companies")
    public ResponseEntity<ExecTimeResDTO> createCompany(@Valid @RequestBody CompanyCreateReqDTO companyCreateReqDTO) {
        ;
        return ResponseEntity.ok(companyService.createCompany(companyCreateReqDTO));
    }

    @GetMapping("/companies/{id}")
    public ResponseEntity<CompanyResDTO> getCompany(@PathVariable int id) {
        return ResponseEntity.ok(new CompanyResDTO(companyService.getCompany(id)));
    }

    @GetMapping("/tradeForce/{id}")
    public ResponseEntity<ExecTimeResDTO> tradeForce(@PathVariable int id) {
        ExecDetailsHelper execDetailsHelper = new ExecDetailsHelper();
        log.debug("Forced trade for company: " + id);
        tradeService.startThread(id);
        return ResponseEntity.ok(new ExecTimeResDTO(new ExecDetailsResDTO(0, execDetailsHelper.getExecTime())));
    }

    @GetMapping("/companies")
    public ResponseEntity<CompanyInfoResDTO> getCompanies() {
        return ResponseEntity.ok(companyService.getCompanies());
    }
}
