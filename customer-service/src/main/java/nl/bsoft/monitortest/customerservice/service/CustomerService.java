package nl.bsoft.monitortest.customerservice.service;

import lombok.AllArgsConstructor;
import nl.bsoft.monitortest.customerservice.model.CustomerDTO;
import nl.bsoft.monitortest.customerservice.repositories.CustomerRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerDTO getCustomerDTO(Integer id) {
        return getCustomerDTOForId(id);
    }

    @Cacheable(value = "custCache")
    public CustomerDTO getCustomerDTOForId(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }
}
