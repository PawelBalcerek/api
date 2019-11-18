package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.BuyOffer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BuyOfferRepository  extends JpaRepository<BuyOffer, Integer> {

    List<BuyOffer> findByIsValid (Boolean valid);

    Optional<BuyOffer> findById(Integer id);

    @Query("SELECT b FROM " +
            "BuyOffer b INNER JOIN Resource r ON b.resource = r " +
            "INNER JOIN User u ON r.user = u " +
            "WHERE u.id = :user")
    List<BuyOffer> getAllBuyOffersForUserId(@Param("user") int user);

    @Query("SELECT b FROM BuyOffer b INNER JOIN Resource r ON r = b.resource " +
            "INNER JOIN Company c ON r.company = c " +
            "WHERE b.isValid = true AND b.amount > 0 AND c.id = :companyId " +
            "ORDER BY b.maxPrice DESC, b.date ASC")
    List<BuyOffer> getAllPendingBuyOffersForCompanyId(@Param("companyId") int companyid, Pageable pageable);
}
