package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.DTOs.Response.*;
import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Exceptions.CompanyAlreadyExistsExceptions;
import AI.dtapijava.Exceptions.UserNotFoundExceptions;
import AI.dtapijava.Infrastructure.Util.UserUtils;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.ResourceRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    public ExecTimeResDTO createCompany(CompanyCreateReqDTO companyCreateReqDTO) {
        ExecDetailsHelper execDetailsHelper = new ExecDetailsHelper();

        if(companyRepository.findByName(companyCreateReqDTO.getName()).isPresent())
            throw new CompanyAlreadyExistsExceptions("Company already exists in database");
        execDetailsHelper.setStartDbTime(OffsetDateTime.now());
        User owner = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(() -> new UserNotFoundExceptions("User not found!"));
        execDetailsHelper.addNewDbTime();
        Company company = new Company();
        company.setName(companyCreateReqDTO.getName());

        Resource resource = Resource.builder().company(company).amount(companyCreateReqDTO.getResourceAmount())
                .user(owner).build();

        execDetailsHelper.setStartDbTime(OffsetDateTime.now());
        resourceRepository.save(resource);
        execDetailsHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(
                execDetailsHelper.getDbTime(),
                execDetailsHelper.getExecTime()
        ));
    }

    public Company getCompany(int id) {
        return companyRepository.getOne(id);
    }

    public CompanyInfoResDTO getCompanies() {
        //TODO do przerobienia
        ExecDetailsHelper execHelper = new ExecDetailsHelper();

        execHelper.setStartDbTime(OffsetDateTime.now());
        List<CompanyNewResDTO> companies = companyRepository.getAllCompanies();
        List<CompanyNewResDTO> companiesOther = companyRepository.getOtherCompanies();

        List<Company> _allCompanies = companyRepository.findAll();
        List<CompanyNewResDTO> _allCompaniesDTO = _allCompanies.stream().map(CompanyNewResDTO::new).collect(Collectors.toList());
        companies.forEach(c-> {

            CompanyNewResDTO cmp = _allCompaniesDTO.stream().filter(a->a.getId().equals(c.getId())).findFirst().orElse(null);
            if (cmp!=null){
              cmp.setIndexPrice(c.getIndexPrice());
            }
             });

        execHelper.addNewDbTime();


        return new CompanyInfoResDTO(_allCompaniesDTO, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

}
