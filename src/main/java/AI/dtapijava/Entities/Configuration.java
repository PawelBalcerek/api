package AI.dtapijava.Entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "configurations")
public class Configuration {

    @Id
    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer number;

}
