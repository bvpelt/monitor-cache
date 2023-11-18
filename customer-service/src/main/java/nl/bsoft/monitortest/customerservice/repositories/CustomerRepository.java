package nl.bsoft.monitortest.customerservice.repositories;

import nl.bsoft.monitortest.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, PagingAndSortingRepository<Customer, Integer> {
}
