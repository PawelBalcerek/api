package AI.dtapijava.Entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "configurations")
public class Configuration {

    @Id
    @Column(length = 255, unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer number;

}
