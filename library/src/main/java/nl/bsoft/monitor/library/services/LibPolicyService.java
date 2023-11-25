package nl.bsoft.monitor.library.services;

import nl.bsoft.monitor.library.domain.Policy;

import java.util.List;

public interface LibPolicyService {

    List<Policy> getAll();

    Policy add(Policy policy);

    Policy update(Policy policy);

    void delete(long id);

    Policy getPolicyById(long id);
}
