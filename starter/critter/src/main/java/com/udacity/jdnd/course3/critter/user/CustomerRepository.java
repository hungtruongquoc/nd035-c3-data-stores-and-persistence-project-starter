package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    public List<CustomerEntity> findAll();
}