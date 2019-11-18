package AI.dtapijava.Services;

import AI.dtapijava.Components.ExecDetailsHelper;
import AI.dtapijava.DTOs.Request.ConfigurationReqDTO;
import AI.dtapijava.DTOs.Response.ExecDetailsResDTO;
import AI.dtapijava.DTOs.Response.ExecTimeResDTO;
import AI.dtapijava.Entities.Configuration;
import AI.dtapijava.Repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    public ExecTimeResDTO setConfiguration(ConfigurationReqDTO configurationReqDTO) {
        ExecDetailsHelper execDetailsHelper = new ExecDetailsHelper();

        execDetailsHelper.setStartDbTime(OffsetDateTime.now());
        Configuration configuration = configurationRepository.findById(configurationReqDTO.getName())
                .orElse(new Configuration(
                        configurationReqDTO.getName(), configurationReqDTO.getValue()
                ));
        execDetailsHelper.addNewDbTime();
        configuration.setNumber(configurationReqDTO.getValue());

        execDetailsHelper.setStartDbTime(OffsetDateTime.now());
        configurationRepository.save(configuration);
        execDetailsHelper.addNewDbTime();

        return new ExecTimeResDTO(new ExecDetailsResDTO(
                execDetailsHelper.getDbTime(),
                execDetailsHelper.getExecTime()
        ));
    }

}
