package nl.bsoft.monitor.library.repositories;

import nl.bsoft.monitor.library.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PolicyRepository extends JpaRepository<Policy, Long>, PagingAndSortingRepository<Policy, Long> {
}
