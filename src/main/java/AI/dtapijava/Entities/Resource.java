package AI.dtapijava.Entities;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(targetEntity = Company.class, fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "comp_id", nullable = false)
    private Company company;

    @OneToMany(targetEntity = SellOffer.class, mappedBy = "resource", cascade = CascadeType.REMOVE)
    private List<SellOffer> sellOffers;

    @OneToMany(targetEntity = BuyOffer.class, mappedBy = "resource", cascade = CascadeType.REMOVE)
    private List<BuyOffer> buyOffers;

    @Column(nullable = false)
    private Integer amount;


}
