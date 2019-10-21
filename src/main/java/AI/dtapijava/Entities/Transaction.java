package AI.dtapijava.Entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Builder
@Table(schema = "public", name = "Transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @ManyToOne(targetEntity = SellOffer.class, fetch = FetchType.EAGER)
    @JoinColumn
    private SellOffer sell_offer_id;

    @ManyToOne(targetEntity = BuyOffer.class, fetch = FetchType.EAGER)
    @JoinColumn
    private BuyOffer buy_offer_id;

    @Column(nullable = false)
    private OffsetDateTime date;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Float price;
}
