package AI.dtapijava.Entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@Table(schema = "public", name = "Buy_Offers")
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
    private double maxPrice;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false, name = "is_valid")
    private Boolean isValid;
}
