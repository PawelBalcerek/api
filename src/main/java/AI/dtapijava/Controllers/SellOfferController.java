package AI.dtapijava.Controllers;

import AI.dtapijava.DTOs.Request.AddSellOfferReqDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.DTOs.Response.SellOfferResDTO;
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
    public ResponseEntity<SellOfferResDTO> getSellOffer (@PathVariable int id) {
        return ResponseEntity.ok(new SellOfferResDTO(sellOfferService.getSellOffer(id)));
    }

    @GetMapping("/sellOffers/isValid")
    public  ResponseEntity<List<SellOfferResDTO>> getSellOffersValid () {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.TRUE).stream().map(SellOfferResDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/sellOffers/isNotValid")
    public  ResponseEntity<List<SellOfferResDTO>> getSellOffersNotValid () {
        return ResponseEntity.ok(sellOfferService.getSellOffersValid(Boolean.FALSE).stream().map(SellOfferResDTO::new).collect(Collectors.toList()));
    }

    @GetMapping("/sellOffers/")
    public  ResponseEntity<List<SellOfferResDTO>> getSellOffers () {
        return ResponseEntity.ok(sellOfferService.getSellOffers().stream().map(SellOfferResDTO::new).collect(Collectors.toList()));
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
