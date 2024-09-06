package com.enb.curtainmanagement.customer.repository;

import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface CustomerRepository extends JpaRepository<CustomerEntity, String>, JpaSpecificationExecutor<CustomerEntity> {

    boolean existsCustomerEntityByIdNumber(String idNumber);

    }



