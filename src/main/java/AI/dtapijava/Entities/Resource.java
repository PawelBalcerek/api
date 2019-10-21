package AI.dtapijava.Entities;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(schema = "public", name = "Resources")
public class Resource {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @ManyToOne (targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id", nullable = false)
    private User user;

    @ManyToOne (targetEntity = Company.class, fetch = FetchType.EAGER)
    @JoinColumn (name = "comp_id", nullable = false)
    private Company comp;
   


    @Column(nullable = false)
    private Integer amount;


}
