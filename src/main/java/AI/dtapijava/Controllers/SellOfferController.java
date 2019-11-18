package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Request.AddSellOfferReqDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.DTOs.Response.SellOfferExtResDTO;
import AI.dtapijava.DTOs.Response.SellOffersResDTO;
import AI.dtapijava.Services.SellOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SellOfferController {

    @Autowired
    private SellOfferService sellOfferService;

    @GetMapping("/sell-offers/{id}")
    public ResponseEntity<SellOfferExtResDTO> getSellOffer(@PathVariable int id) {
        return ResponseEntity.ok(sellOfferService.getSellOffer(id));
    }

    @GetMapping("/sell-offers/isValid")
    public ResponseEntity<SellOffersResDTO> getSellOffersValid() {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.TRUE));
    }

    @GetMapping("/sell-offers/isNotValid")
    public ResponseEntity<SellOffersResDTO> getSellOffersNotValid() {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.FALSE));
    }

    @GetMapping("/sell-offers")
    public ResponseEntity<SellOffersResDTO> getSellOffers() {
        return ResponseEntity.ok(sellOfferService.getSellOffers());
    }

    @PostMapping("/sell-offers")
    public ResponseEntity<ExecTimeResDTO> addSellOffer(@Valid @RequestBody AddSellOfferReqDTO addSellOfferReqDTO) {
        return ResponseEntity.ok(sellOfferService.addSellOffer(addSellOfferReqDTO));
    }

    @PutMapping("/sell-offers/{id}")
    public ResponseEntity<ExecTimeResDTO> withdrawSellOffer(@PathVariable int id) {
        return ResponseEntity.ok(sellOfferService.withdrawSellOffer(id));
    }
}
