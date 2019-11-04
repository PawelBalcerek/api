package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Boolean existsByNameOrEmail(String name,String email);

    Optional<User> findById(Integer id);

    Optional<User> findByNameOrEmail(String name, String email);


}
