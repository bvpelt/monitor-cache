package nl.bsoft.monitor.library.services;

import nl.bsoft.monitor.library.domain.Customer;

import java.util.List;

public interface LibCustomerService {
    List<Customer> getAll();

    Customer add(Customer customer);

    Customer update(Customer customer);

    void delete(long id);

    Customer getCustomerById(long id);
}
