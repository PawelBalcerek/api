package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Request.AddBuyOfferReqDTO;
import AI.dtapijava.DTOs.Response.BuyOfferExtResDTO;
import AI.dtapijava.DTOs.Response.BuyOfferResDTO;
import AI.dtapijava.DTOs.Response.BuyOffersResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Services.BuyOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class BuyOfferController {

    @Autowired
    private BuyOfferService buyOfferService;

    @GetMapping("/buy-offers/{id}")
    public ResponseEntity<BuyOfferExtResDTO> getBuyOffer (@PathVariable int id) {
        return ResponseEntity.ok(buyOfferService.getBuyOffer(id));
    }

    @GetMapping("/buy-offers/isValid")
    public  ResponseEntity<BuyOffersResDTO> getBuyOffersValid () {
        return ResponseEntity.ok(buyOfferService.getBuyOffersValid(Boolean.TRUE));
    }

    @GetMapping("/buy-offers/isNotValid")
    public  ResponseEntity<BuyOffersResDTO> getBuyOffersNotValid () {
        return ResponseEntity.ok(buyOfferService.getBuyOffersValid(Boolean.FALSE));
    }

    @GetMapping("/buy-offers")
    public  ResponseEntity<BuyOffersResDTO> getBuyOffers () {
        return ResponseEntity.ok(buyOfferService.getBuyOffers());
    }

    @PostMapping("/buy-offers")
    public ResponseEntity<ExecTimeResDTO> addBuyOffer(@Valid @RequestBody AddBuyOfferReqDTO addBuyOfferReqDTO) {
        return ResponseEntity.ok(buyOfferService.addBuyOffer(addBuyOfferReqDTO));
    }

    @PutMapping("/buy-offers/{id}")
    public ResponseEntity<ExecTimeResDTO> withdrawBuyOffer(@PathVariable int id) {
        return ResponseEntity.ok(buyOfferService.withdrawBuyOffer(id));
    }
}
