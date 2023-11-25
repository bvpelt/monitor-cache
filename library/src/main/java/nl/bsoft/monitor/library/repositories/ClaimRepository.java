package nl.bsoft.monitor.library.repositories;

import nl.bsoft.monitor.library.domain.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long>, PagingAndSortingRepository<Claim, Long> {
}
