package nl.bsoft.monitor.library.services;

import lombok.extern.slf4j.Slf4j;
import nl.bsoft.monitor.library.domain.Customer;
import nl.bsoft.monitor.library.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@CacheConfig(cacheNames = "customerCache")
public class LibCustomerServiceImpl implements LibCustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> getAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer add(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Optional<Customer> optCustomer = this.customerRepository.findById(customer.getId());
        if (!optCustomer.isPresent())
            return null;

        Customer repCustomer = optCustomer.get();
        repCustomer.setCustomerid(customer.getCustomerid());
        repCustomer.setClaimid(customer.getClaimid());
        repCustomer.setPolicyid(customer.getPolicyid());
        return this.customerRepository.save(repCustomer);
    }

    @Override
    public void delete(long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public Customer getCustomerById(long id) {
        return this.customerRepository.findById(id).orElse(null);
    }

}