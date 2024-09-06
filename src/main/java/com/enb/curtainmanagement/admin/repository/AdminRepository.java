package com.enb.curtainmanagement.admin.repository;

import com.enb.curtainmanagement.admin.model.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, String> {

    boolean existsAdminEntityByEmail(final String email);

    Optional<AdminEntity> findAdminEntityByEmail(final String email);

}
