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
@Table(schema = "public", name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @Column(length = 255)
    private String name;

    @OneToMany(targetEntity = Resource.class, mappedBy = "company")
    private List<Resource> resources;

}
