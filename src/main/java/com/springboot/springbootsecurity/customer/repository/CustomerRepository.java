package com.springboot.springbootsecurity.customer.repository;

import com.springboot.springbootsecurity.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CustomerRepository extends JpaRepository<CustomerEntity, String>, JpaSpecificationExecutor<CustomerEntity> {

    boolean existsCustomerEntityByFirstname(final String firstname);

    }



