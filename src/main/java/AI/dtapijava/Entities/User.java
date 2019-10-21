package AI.dtapijava.Entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name="ID", nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(length = 255)
    private String password;

}
