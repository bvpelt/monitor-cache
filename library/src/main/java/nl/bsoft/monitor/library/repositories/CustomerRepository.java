package nl.bsoft.monitor.library.repositories;

import nl.bsoft.monitor.library.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {
}
