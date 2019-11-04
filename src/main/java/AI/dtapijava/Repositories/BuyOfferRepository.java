package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.BuyOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyOfferRepository  extends JpaRepository<BuyOffer, Integer> {

    List<BuyOffer> findByIsValid (Boolean valid);

    @Query("SELECT b FROM " +
            "BuyOffer b INNER JOIN Resource r ON b.resource = r " +
            "INNER JOIN User u ON r.user = u " +
            "WHERE u.id = :user")
    List<BuyOffer> getAllBuyOffersForUserId(@Param("user") int user);
}
