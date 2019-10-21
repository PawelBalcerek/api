package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
