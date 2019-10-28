package AI.dtapijava.Services;

import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.ResourceRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    public boolean createCompany(CompanyCreateReqDTO companyCreateReqDTO) throws Exception {
        Optional<User> owner = Optional.of(userRepository.getOne(companyCreateReqDTO.getUserId()));
        if (!owner.isPresent()) {
            throw new Exception("Specified owner does not exist");
        }

        Company company = new Company();
        company.setName(companyCreateReqDTO.getName());
        companyRepository.save(company);

        Resource resource = new Resource();
        resource.setCompany(company);
        resource.setAmount(companyCreateReqDTO.getResourcesAmount());
        resource.setUser(owner.get());
        resourceRepository.save(resource);

        return false;
    }

    public Company getCompany(int id) {
        return companyRepository.getOne(id);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }
}
