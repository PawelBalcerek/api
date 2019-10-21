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
@Table(schema = "public", name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer ID;

    @Column(nullable = false)
    private Boolean name;

    @Column(nullable = false)
    @Email
    private Boolean email;

    @Column(length = 255)
    private String password;

}
