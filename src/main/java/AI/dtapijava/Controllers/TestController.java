package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Response.ResourceResDTO;
import AI.dtapijava.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
//to remove later
public class TestController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BuyOfferRepository buyOfferRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Autowired
    private SellOfferRepository sellOfferRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/test/user")
    public ResponseEntity<?> testUser(){return ResponseEntity.ok(userRepository.findAll());}
    @GetMapping("/test/company")
    public ResponseEntity<?> testCompany(){
        return ResponseEntity.ok(companyRepository.findAll());
    }
    @GetMapping("/test/buyOffer")
    public ResponseEntity<?> testBuyOffer(){
        return ResponseEntity.ok(buyOfferRepository.findAll());
    }
    @GetMapping("/test/resource")
    public ResponseEntity<?> testResource(){
        return ResponseEntity.ok(resourceRepository.getAllResourcesFromUser(1).stream().map(ResourceResDTO::new).collect(Collectors.toList()));
    }
    @GetMapping("/test/sellOffer")
    public ResponseEntity<?> testSellOffer(){
        return ResponseEntity.ok(sellOfferRepository.findAll());
    }
    @GetMapping("/test/transaction")
    public ResponseEntity<?> testTransaction(){
        return ResponseEntity.ok(transactionRepository.findAll());
    }

}
