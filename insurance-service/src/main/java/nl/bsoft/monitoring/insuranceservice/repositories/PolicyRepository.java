package nl.bsoft.monitoring.insuranceservice.repositories;

import nl.bsoft.monitoring.insuranceservice.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PolicyRepository extends JpaRepository<Policy, Integer>, PagingAndSortingRepository<Policy, Integer> {
}
