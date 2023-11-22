package nl.bsoft.monitoring.insuranceservice.repositories;

import nl.bsoft.monitoring.insuranceservice.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long>, PagingAndSortingRepository<Claim, Long> {
}
