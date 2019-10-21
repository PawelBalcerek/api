package AI.dtapijava.Entities;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "resources")
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
