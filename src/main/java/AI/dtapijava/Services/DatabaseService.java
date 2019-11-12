package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Repositories.CompanyRepository;
import AI.dtapijava.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@Service
public class DatabaseService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Transactional
    public ExecTimeResDTO cleanDatabase() {
        ExecDetailsHelper execDetailsHelper = new ExecDetailsHelper();

        execDetailsHelper.setStartDbTime(OffsetDateTime.now());
        userRepository.cleanMoney();
        companyRepository.deleteAll();
        execDetailsHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(
                execDetailsHelper.getDbTime(),
                execDetailsHelper.getExecTime()
        ));
    }

    public ExecTimeResDTO purgeDatabase() {
        ExecDetailsHelper execDetailsHelper = new ExecDetailsHelper();

        execDetailsHelper.setStartDbTime(OffsetDateTime.now());
        companyRepository.deleteAll();
        userRepository.deleteAll();
        execDetailsHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(
                execDetailsHelper.getDbTime(),
                execDetailsHelper.getExecTime()
        ));
    }
}
