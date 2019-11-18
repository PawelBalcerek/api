package AI.dtapijava.DTOs.Response;

import AI.dtapijava.Entities.Transaction;
import AI.dtapijava.Enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Setter
@Getter
@AllArgsConstructor
public class UserTransactionResDTO {
    private int id;
    private ResourceCompanyResDTO company;
    private int amount;
    private double price;
    private OffsetDateTime date;
    private TransactionType type;

    public UserTransactionResDTO(Transaction transaction, TransactionType type) {
        id = transaction.getID();
        company = new ResourceCompanyResDTO(transaction.getSellOffer().getResource().getCompany());
        amount = transaction.getAmount();
        price = transaction.getPrice();
        date = transaction.getDate();
        this.type = type;
    }
}
