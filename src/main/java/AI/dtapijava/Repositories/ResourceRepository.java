package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Integer> {

    @Query("SELECT r FROM Resource r " +
            "INNER JOIN User u ON r.user = u " +
            "INNER JOIN Company c ON r.company = c " +
            "WHERE u.id = :user")
    List<Resource> getAllResourcesFromUser(@Param("user") Integer user);

    @Query("SELECT r FROM Resource r " +
            "INNER JOIN User u ON r.user = u " +
            "INNER JOIN Company c ON r.company = c " +
            "WHERE c.id = :company")
    List<Resource> getAllResourcesFromCompany(@Param("company") Integer company);

    Optional<Resource> findByUserAndCompany(User user, Company company);
}
