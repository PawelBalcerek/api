package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.DTOs.Response.CompanyInfoResDTO;
import AI.dtapijava.DTOs.Response.CompanyNewResDTO;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Exceptions.UserNotFoundExceptions;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.ResourceRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    public void createCompany(CompanyCreateReqDTO companyCreateReqDTO){
        //jeśli zwraca optional, to moge dac .orElse() lub .orElseThrow()
        User owner = userRepository.findById(companyCreateReqDTO.getUserId())
                //jezeli nie znajdzie rzucam wyjatek
                .orElseThrow(()-> new UserNotFoundExceptions("User not found!"));

            Company company = new Company();
            company.setName(companyCreateReqDTO.getName());
            //obiekt entity mozna tez zbudowac builder'em (lombok)
            Resource resource = Resource.builder().company(company).amount(companyCreateReqDTO.getResourcesAmount())
                                .user(owner).build();
            //jednym strzalem do bazy zapisuje w dwoch tabelach (trzeba ustawic odpowiednia kaskade - w tym przypadku PERSIST)
            resourceRepository.save(resource);

    }

    public Company getCompany(int id) {
        return companyRepository.getOne(id);
    }

    public CompanyInfoResDTO getCompanies() {
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<CompanyNewResDTO> companies = companyRepository.getAllCompanies();
        execHelper.addNewDbTime();

        return new CompanyInfoResDTO(companies, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

    public Double getPrice(Company company){
        //company.getResources().stream().findFirst().get().getSellOffers().stream().findFirst().get().getPrice()
        return null;
    }
}
