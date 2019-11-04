package AI.dtapijava.Repositories;

import AI.dtapijava.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    @Query("SELECT c, t.price from "+
    "Company c inner join Resource r on c = r.company "+
    "inner join SellOffer s on r = s.resource "+
    "inner join Transaction t on s = t.sellOffer "+
    "where t.date in( "+
    "select max(t.date) from "+
    "Company c inner join Resource r on c = r.company "+
    "inner join SellOffer s on r = s.resource "+
    "inner join Transaction t on s = t.sellOffer "+
    "group by c.id "+
    "order by c.id )")
    List<Tuple> getAllCompanies();
}
