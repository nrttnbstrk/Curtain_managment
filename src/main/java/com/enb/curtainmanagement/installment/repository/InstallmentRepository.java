package com.enb.curtainmanagement.installment.repository;

import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface InstallmentRepository extends JpaRepository<InstallmentEntity, String>, JpaSpecificationExecutor<InstallmentEntity> {

    boolean existsInstallmentEntitiesById(String id);

    }



