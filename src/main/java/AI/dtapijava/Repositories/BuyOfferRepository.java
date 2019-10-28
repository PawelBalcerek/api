package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.BuyOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyOfferRepository  extends JpaRepository<BuyOffer, Integer> {

    List<BuyOffer> findByIsValid (Boolean valid);
}
