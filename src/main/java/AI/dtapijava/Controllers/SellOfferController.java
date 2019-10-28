package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Response.SellOfferResDTO;
import AI.dtapijava.Services.SellOfferService;
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
public class SellOfferController {

    @Autowired
    private SellOfferService sellOfferService;

    @GetMapping("/sellOffer/{id}")
    public ResponseEntity<SellOfferResDTO> getSellOffer (@PathVariable int id) {
        return ResponseEntity.ok(new SellOfferResDTO(sellOfferService.getSellOffer(id)));
    }

    @GetMapping("/sellOffers/isValid")
    public  ResponseEntity<List<SellOfferResDTO>> getSellOffersValid (@PathVariable Boolean valid) {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.TRUE).stream().map(SellOfferResDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/sellOffers/isNotValid")
    public  ResponseEntity<List<SellOfferResDTO>> getSellOffersNotValid (@PathVariable Boolean valid) {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.FALSE).stream().map(SellOfferResDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/sellOffers/")
    public  ResponseEntity<List<SellOfferResDTO>> getSellOffers (@PathVariable Boolean valid) {
        return ResponseEntity.ok(sellOfferService.getSellOffers().stream().map(SellOfferResDTO::new).collect(Collectors.toList()));
    }
}
