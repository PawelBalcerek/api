package AI.dtapijava.Repositories;

import AI.dtapijava.DTOs.Response.CompanyNewResDTO;
import AI.dtapijava.Entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findById(Integer id);

    @Query("SELECT new AI.dtapijava.DTOs.Response.CompanyNewResDTO(c, t.price) from " +
            "Company c inner join Resource r on c = r.company " +
            "left join SellOffer s on r = s.resource " +
            "left join Transaction t on s = t.sellOffer " +
            "where t.date in( " +
            "select max(t.date) from " +
            "Company c inner join Resource r on c = r.company " +
            "left join SellOffer s on r = s.resource " +
            "left join Transaction t on s = t.sellOffer " +
            "group by c.id " +
            "order by c.id )")
    List<CompanyNewResDTO> getAllCompanies();

    @Query("SELECT new AI.dtapijava.DTOs.Response.CompanyNewResDTO(c) from " +
            "Company c inner join Resource r on c = r.company " +
            "left join SellOffer s on r = s.resource " +
            "WHERE s.resource IS NULL")
    List<CompanyNewResDTO> getOtherCompanies();

    @Query("SELECT new AI.dtapijava.DTOs.Response.CompanyNewResDTO(c) from " +
            "Company c WHERE id NOT IN :ids")
    List<CompanyNewResDTO> getCompaniesExcept(int[] ids);
}
