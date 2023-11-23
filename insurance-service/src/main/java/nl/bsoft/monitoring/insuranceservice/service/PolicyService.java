package nl.bsoft.monitoring.insuranceservice.service;

import nl.bsoft.monitoring.insuranceservice.domain.Policy;

import java.util.List;

public interface PolicyService {

    List<Policy> getAll();

    Policy add(Policy policy);

    Policy update(Policy policy);

    void delete(long id);

    Policy getPolicyById(long id);
}
