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
@Table(schema = "public", name = "sell_offers")
public class SellOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @ManyToOne(targetEntity = Resource.class)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private OffsetDateTime date;

    @Column(nullable = false, name = "is_valid")
    private Boolean isValid;

    @Column(nullable = false, name = "start_amount")
    private Integer startAmount;
}
