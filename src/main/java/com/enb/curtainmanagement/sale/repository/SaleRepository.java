package com.enb.curtainmanagement.sale.repository;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.math.BigDecimal;

public interface SaleRepository extends JpaRepository<SaleEntity, String>,JpaSpecificationExecutor<SaleEntity> {

    List<SaleEntity> findByCustomerId(String customerId);

    boolean existsSaleEntityById(final String id);

    @Query(value = "SELECT SUM(CAST(s.amount AS DECIMAL(10,2))) FROM sale s WHERE s.product_id = :productId", nativeQuery = true)
    BigDecimal calculateTotalAmountByProductId(@Param("productId") String productId);
}

