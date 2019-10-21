package AI.dtapijava.Entities;

import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "buy_offers")
public class BuyOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @ManyToOne(targetEntity = Resource.class)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false, name = "max_price")
    private Double maxPrice;

    @Column(nullable = false)
    private OffsetDateTime date;

    @Column(nullable = false, name = "is_valid")
    private Boolean isValid;

    @OneToMany(targetEntity = Transaction.class, mappedBy = "buyOffer")
    private List<Transaction> transactions;
}
