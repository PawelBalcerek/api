package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Request.AddBuyOfferReqDTO;
import AI.dtapijava.DTOs.Response.BuyOfferResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Services.BuyOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class BuyOfferController {

    @Autowired
    private BuyOfferService buyOfferService;

    @GetMapping("/buyOffers/{id}")
    public ResponseEntity<BuyOfferResDTO> getBuyOffer (@PathVariable int id) {
        return ResponseEntity.ok(new BuyOfferResDTO(buyOfferService.getBuyOffer(id)));
    }

    @GetMapping("/buyOffers/isValid")
    public  ResponseEntity<List<BuyOfferResDTO>> getBuyOffersValid () {
        return ResponseEntity.ok(buyOfferService.getBuyOffersValid(Boolean.TRUE).stream().map(BuyOfferResDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/buyOffers/isNotValid")
    public  ResponseEntity<List<BuyOfferResDTO>> getBuyOffersNotValid () {
        return ResponseEntity.ok(buyOfferService.getBuyOffersValid(Boolean.FALSE).stream().map(BuyOfferResDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/buyOffers/")
    public  ResponseEntity<List<BuyOfferResDTO>> getBuyOffers () {
        return ResponseEntity.ok(buyOfferService.getBuyOffers().stream().map(BuyOfferResDTO::new).collect(Collectors.toList()));
    }

    @PostMapping("/buyOffers")
    public ResponseEntity<ExecTimeResDTO> addBuyOffer(AddBuyOfferReqDTO addBuyOfferReqDTO) {
        return ResponseEntity.ok(buyOfferService.addBuyOffer(addBuyOfferReqDTO));
    }

    @PutMapping("/buyOffers/{id}")
    public ResponseEntity<ExecTimeResDTO> withdrawBuyOffer(@PathVariable int id) {
        return ResponseEntity.ok(buyOfferService.withdrawBuyOffer(id));
    }
}
