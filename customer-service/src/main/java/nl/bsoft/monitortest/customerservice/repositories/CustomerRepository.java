package nl.bsoft.monitortest.customerservice.repositories;

import nl.bsoft.monitortest.customerservice.model.CustomerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Integer>, PagingAndSortingRepository<CustomerDTO, Integer> {
}
