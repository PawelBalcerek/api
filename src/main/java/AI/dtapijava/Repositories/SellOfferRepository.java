package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.SellOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellOfferRepository extends JpaRepository<SellOffer, Integer> {
    List<SellOffer> findByIsValid (Boolean valid);
}
