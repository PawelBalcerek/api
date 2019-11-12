package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.CompanyCreateReqDTO;
import AI.dtapijava.DTOs.Response.CompanyInfoResDTO;
import AI.dtapijava.DTOs.Response.CompanyNewResDTO;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Entities.Company;
import AI.dtapijava.Entities.Resource;
import AI.dtapijava.Entities.User;
import AI.dtapijava.Exceptions.UserNotFoundExceptions;
import AI.dtapijava.Infrastructure.Util.UserUtils;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.ResourceRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.time.OffsetDateTime;
import java.util.ArrayList;
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

    public ExecTimeResDTO createCompany(CompanyCreateReqDTO companyCreateReqDTO){
        ExecDetailsHelper execDetailsHelper = new ExecDetailsHelper();
        //TODO sprawdzić, czy firma o takiej nazwie istnieje w bazie (czy mogą być dwie firmy o tej samej nazwie?)
        execDetailsHelper.setStartDbTime(OffsetDateTime.now());
        User owner = userRepository.findById(UserUtils.getCurrentUserId())
                .orElseThrow(()-> new UserNotFoundExceptions("User not found!"));
        execDetailsHelper.addNewDbTime();
            Company company = new Company();
            company.setName(companyCreateReqDTO.getName());

            Resource resource = Resource.builder().company(company).amount(companyCreateReqDTO.getResourcesAmount())
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
        execHelper.addNewDbTime();
        List<CompanyNewResDTO> allCompanies = new ArrayList<>(companies.size()+companiesOther.size());
        allCompanies.addAll(companies);
        allCompanies.addAll(companiesOther);

        return new CompanyInfoResDTO(allCompanies, new ExecDetailsResDTO(execHelper.getDbTime(), execHelper.getExecTime()));
    }

}
