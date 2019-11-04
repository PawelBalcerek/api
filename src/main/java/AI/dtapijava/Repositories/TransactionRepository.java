package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query("SELECT t FROM " +
            "Transaction t INNER JOIN SellOffer s ON t.sellOffer = s " +
            "INNER JOIN Resource r ON s.resource = r " +
            "INNER JOIN User u ON r.user = u " +
            "WHERE u.id = :user")
    List<Transaction> getAllSellTransactionsForUserId(@Param("user") int user);

    @Query("SELECT t FROM " +
            "Transaction t INNER JOIN BuyOffer b ON t.buyOffer = b " +
            "INNER JOIN Resource r ON s.resource = r " +
            "INNER JOIN User u ON r.user = u " +
            "WHERE u.id = :user")
    List<Transaction> getAllBuyTransactionsForUserId(@Param("user") int user);
}
