package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserTransactionResDTO {
    private long id;
    private ResourceCompanyResDTO company;
    private int amount;
    private double price;
    private OffsetDateTime date;

    public UserTransactionResDTO(Transaction transaction) {
        id = transaction.getID();
        company = new ResourceCompanyResDTO(transaction.getSellOffer().getResource().getCompany());
        amount = transaction.getAmount();
        price = transaction.getPrice();
        date = transaction.getDate();
    }
}
