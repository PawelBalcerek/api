package AI.dtapijava.Services;


import AI.dtapijava.DTOs.Response.ResourceResDTO;
import AI.dtapijava.Repositories.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public List<ResourceResDTO> getResourcesToUser(int userId) {
        return resourceRepository.getAllResourcesFromUser(userId).stream().map(ResourceResDTO::new).collect(Collectors.toList());
    }

    public List<ResourceResDTO> getResourcesToCompany(int companyId) {
        return resourceRepository.getAllResourcesFromCompany(companyId).stream().map(ResourceResDTO::new).collect(Collectors.toList());
    }
}
