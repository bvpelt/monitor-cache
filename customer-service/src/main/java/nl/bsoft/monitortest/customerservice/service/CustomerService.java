package nl.bsoft.monitortest.customerservice.service;

import lombok.AllArgsConstructor;
import nl.bsoft.monitortest.customerservice.model.Customer;
import nl.bsoft.monitortest.customerservice.repositories.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Cacheable(value = "itemCache")
    public Customer getItemForId(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
