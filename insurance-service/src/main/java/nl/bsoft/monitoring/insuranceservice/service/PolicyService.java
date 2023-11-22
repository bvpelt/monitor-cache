package nl.bsoft.monitoring.insuranceservice.service;
import nl.bsoft.monitoring.insuranceservice.domain.Policy;

import java.util.List;

public interface PolicyService {

    public List<Policy> getAll();

    public Policy add(Policy policy);

    public Policy update(Policy policy);

    public void delete(long id);

    public Policy getPolicyById(long id);
}
