package AI.dtapijava.Services;

import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.ResourceRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Optional<User> owner = userRepository.findById(companyCreateReqDTO.getUserId());
        if (owner.isPresent()) {
            Company company = new Company();
            company.setName(companyCreateReqDTO.getName());
            companyRepository.save(company);

            Resource resource = new Resource();
            resource.setCompany(company);
            resource.setAmount(companyCreateReqDTO.getResourcesAmount());
            resource.setUser(owner.get());
            resourceRepository.save(resource);
        }

    }

    public Company getCompany(int id) {
        return companyRepository.getOne(id);
    }

    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }
}
