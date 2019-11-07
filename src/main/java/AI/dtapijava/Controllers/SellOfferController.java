package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Request.AddSellOfferReqDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.DTOs.Response.SellOfferExtResDTO;
import AI.dtapijava.DTOs.Response.SellOfferResDTO;
import AI.dtapijava.DTOs.Response.SellOffersResDTO;
import AI.dtapijava.Services.SellOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SellOfferController {

    @Autowired
    private SellOfferService sellOfferService;

    @GetMapping("/sellOffers/{id}")
    public ResponseEntity<SellOfferExtResDTO> getSellOffer (@PathVariable int id) {
        return ResponseEntity.ok(sellOfferService.getSellOffer(id));
    }

    @GetMapping("/sellOffers/isValid")
    public  ResponseEntity<SellOffersResDTO> getSellOffersValid () {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.TRUE));
    }

    @GetMapping("/sellOffers/isNotValid")
    public  ResponseEntity<SellOffersResDTO> getSellOffersNotValid () {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.FALSE));
    }

    @GetMapping("/sellOffers/")
    public  ResponseEntity<SellOffersResDTO> getSellOffers () {
        return ResponseEntity.ok(sellOfferService.getSellOffers());
    }

    @PostMapping("/sellOffers")
    public ResponseEntity<ExecTimeResDTO> addSellOffer(AddSellOfferReqDTO addSellOfferReqDTO) {
        return ResponseEntity.ok(sellOfferService.addSellOffer(addSellOfferReqDTO));
    }

    @PutMapping("/sellOffers/{id}")
    public ResponseEntity<ExecTimeResDTO> withdrawSellOffer(@PathVariable int id) {
        return ResponseEntity.ok(sellOfferService.withdrawSellOffer(id));
    }
}
