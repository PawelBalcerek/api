package AI.dtapijava.Entities;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @ManyToOne(targetEntity = SellOffer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "sell_offer_id")
    private SellOffer sellOffer;

    @ManyToOne(targetEntity = BuyOffer.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "buy_offer_id")
    private BuyOffer buyOffer;

    @Column(nullable = false)
    private OffsetDateTime date;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Double price;
}
