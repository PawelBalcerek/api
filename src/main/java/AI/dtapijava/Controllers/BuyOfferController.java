package AI.dtapijava.Controllers;


import AI.dtapijava.DTOs.Response.BuyOfferResDTO;
import AI.dtapijava.Services.BuyOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")

public class BuyOfferController {

    @Autowired
    private BuyOfferService buyOfferService;

    @GetMapping("/buyOffer/{id}")
    public ResponseEntity<BuyOfferResDTO> getBuyOffer (@PathVariable int id) {
        return ResponseEntity.ok(new BuyOfferResDTO(buyOfferService.getBuyOffer(id)));

    }
    @GetMapping("/buyOffers/{valid}")
    public  ResponseEntity<List<BuyOfferResDTO>> getBuyOffers (@PathVariable Boolean valid) {
        if (valid.equals(true)) return ResponseEntity.ok(buyOfferService.getBuyOffersValid(Boolean.TRUE).stream().map(BuyOfferResDTO::new).collect(Collectors.toList()));
        else if (valid.equals(false)) return ResponseEntity.ok(buyOfferService.getBuyOffersValid(Boolean.FALSE).stream().map(BuyOfferResDTO::new).collect(Collectors.toList()));
        else return ResponseEntity.ok(buyOfferService.getBuyOffers().stream().map(BuyOfferResDTO::new).collect(Collectors.toList()));
    }

}
