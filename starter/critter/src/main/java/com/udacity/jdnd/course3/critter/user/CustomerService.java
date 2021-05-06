package com.udacity.jdnd.course3.critter.user;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final CustomerRepository repo;

    public CustomerService(CustomerRepository customerRepository) {
        this.repo = customerRepository;
    }

    public CustomerEntity addCustomer(CustomerEntity customer) {
        return repo.save(customer);
    }

    public CustomerEntity getCustomerById(Long customerId) {
        return repo.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("customer id " + customerId + " does not exist in the DB"));
    }

    public List<CustomerEntity> getAllCustomers() {
        return repo.findAll();
    }
}