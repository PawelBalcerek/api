package AI.dtapijava.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class UserTransactionsResDTO {
    private List<UserTransactionResDTO> transactions;
    private ExecDetailsResDTO execDetails;
}
