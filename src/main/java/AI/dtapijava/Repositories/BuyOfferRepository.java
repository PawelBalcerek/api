package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.BuyOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyOfferRepository  extends JpaRepository<BuyOffer, Integer> {
}
