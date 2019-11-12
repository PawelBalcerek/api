package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfigurationRepository extends JpaRepository<Configuration, String> {


    @Override
    Optional<Configuration> findById(String s);
}
