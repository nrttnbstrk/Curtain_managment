package com.springboot.springbootsecurity.customer.repository;

import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    boolean existsCustomerEntityByfirstname(final String firstname);

}
