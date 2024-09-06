package com.enb.curtainmanagement.sale.service.impl;

import com.enb.curtainmanagement.customer.model.entity.CustomerEntity;
import com.enb.curtainmanagement.customer.repository.CustomerRepository;
import com.enb.curtainmanagement.installment.service.InstallmentCreateService;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentCreateRequest;
import com.enb.curtainmanagement.sale.exception.InsufficientAmountException;
import com.enb.curtainmanagement.subProduct.model.entity.SubProductEntity;
import com.enb.curtainmanagement.subProduct.repository.SubProductRepository;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.sale.exception.SaleAlreadyExistException;
import com.enb.curtainmanagement.sale.exception.SaleNotFoundException;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.request.SaleCreateRequest;
import com.enb.curtainmanagement.sale.model.entity.SaleEntity;
import com.enb.curtainmanagement.sale.model.mapper.SaleCreateRequestToSaleEntityMapper;
import com.enb.curtainmanagement.sale.model.mapper.SaleEntityToSaleMapper;
import com.enb.curtainmanagement.sale.repository.SaleRepository;
import com.enb.curtainmanagement.sale.service.SaleCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SaleCreateServiceImpl implements SaleCreateService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private final SubProductRepository subProductRepository;
    private final CustomerRepository customerRepository;
    private final InstallmentCreateService installmentCreateService;
    private final SaleCreateRequestToSaleEntityMapper saleCreateRequestToSaleEntityMapper =
            SaleCreateRequestToSaleEntityMapper.initialize();

    private final SaleEntityToSaleMapper saleEntityToSaleMapper = SaleEntityToSaleMapper.initialize();

    @Override
    @Transactional
    public Sale createSale(SaleCreateRequest saleCreateRequest) {
        // Convert request to entity for saving
        final SaleEntity saleEntityToBeSave = saleCreateRequestToSaleEntityMapper.mapForSaving(saleCreateRequest);

        // Calculate saleAmount
        BigDecimal weight = new BigDecimal(saleEntityToBeSave.getWeight());
        BigDecimal stack = new BigDecimal(saleEntityToBeSave.getStack());
        BigDecimal waste = new BigDecimal(saleEntityToBeSave.getWaste());
        BigDecimal saleAmount = weight.multiply(stack).add(waste);
        saleEntityToBeSave.setAmount(String.valueOf(saleAmount));

        // Save the sale entity
        SaleEntity savedSaleEntity = saleRepository.save(saleEntityToBeSave);

        // Update product and sub-product amounts
        updateProductAndSubProductAmounts(savedSaleEntity);

        // Eğer installment true ise taksitleri oluştur
        if (Boolean.parseBoolean(saleCreateRequest.getInstallment())) {
            createInstallments(savedSaleEntity, saleCreateRequest.getTotalPrice(),
                    saleCreateRequest.getInstallmentQuantity(),
                    Boolean.parseBoolean(saleCreateRequest.getInstallmentToday()));
        }

        return saleEntityToSaleMapper.map(savedSaleEntity);
    }

    private void createInstallments(SaleEntity saleEntity, String totalPrice, String installmentQuantity, boolean installmentToday) {
        // Total price ve installment quantity BigDecimal'a çevriliyor
        BigDecimal totalPriceValue = new BigDecimal(totalPrice);
        int installmentCount = Integer.parseInt(installmentQuantity);

        // Her taksit için fiyat hesaplama
        BigDecimal installmentAmount = totalPriceValue.divide(new BigDecimal(installmentCount), RoundingMode.HALF_UP);

        // İşlemin yapıldığı anı almak için LocalDateTime.now() kullanıyoruz
        LocalDateTime createdDate = LocalDateTime.now();

        for (int i = 1; i <= installmentCount; i++) {
            // Installment kaydını oluştur
            InstallmentCreateRequest installmentCreateRequest = new InstallmentCreateRequest();

            // SaleEntity'den InstallmentCreateRequest alanlarının doldurulması
            installmentCreateRequest.setCustomerId(saleEntity.getCustomerId()); // Customer Id
            installmentCreateRequest.setSaleId(saleEntity.getId()); // Sale Id
            installmentCreateRequest.setInstallmentPrice(installmentAmount); // Taksit fiyatı
            installmentCreateRequest.setInstallmentWhich(String.valueOf(i)); // Taksit numarası (kaçıncı taksit)
            installmentCreateRequest.setStatus("BEKLIYOR"); // Varsayılan taksit durumu
            installmentCreateRequest.setTotalPrice(totalPriceValue); // Toplam fiyat
            installmentCreateRequest.setInstallmentQuantity(installmentCount); // Taksit sayısı

            // createdDate için
            installmentCreateRequest.setCreatedDate(createdDate.toLocalDate()); // İşlemin yapıldığı an (CREATED_DATE)

            // installmentDate için - Eğer installmentToday true ise bugünden başlar, false ise 1 ay sonra başlar
            if (installmentToday) {
                installmentCreateRequest.setInstallmentDate(createdDate.toLocalDate().plusMonths(i - 1)); // Bugünden başlıyor
            } else {
                installmentCreateRequest.setInstallmentDate(createdDate.toLocalDate().plusMonths(i)); // 1 ay sonra başlıyor
            }

            // Installment kaydını InstallmentCreateService aracılığıyla oluştur
            installmentCreateService.createInstallment(installmentCreateRequest);
        }
    }

    private void checkUniquenessIdNumber(final String saleId) {
        if (saleRepository.existsSaleEntityById(saleId)) {
            throw new SaleAlreadyExistException("Bu Id ile başka bir satış zaten mevcut");
        }
    }

    private void updateProductAndSubProductAmounts(SaleEntity saleEntity) {
        ProductEntity product = productRepository.findById(saleEntity.getProductId())
                .orElseThrow(() -> new SaleNotFoundException("Belirtilen URUN mevcut değil."));

        SubProductEntity subProduct = subProductRepository.findById(saleEntity.getSubProductId())
                .orElseThrow(() -> new SaleNotFoundException("Belirtilen ALT URUN mevcut değil."));

        CustomerEntity customer = customerRepository.findById(saleEntity.getCustomerId())
                .orElseThrow(() -> new SaleNotFoundException("Belirtilen MÜŞTERİ mevcut değil."));

        // Convert weight, stack, and waste from String to BigDecimal
        BigDecimal weight = new BigDecimal(saleEntity.getWeight());
        BigDecimal stack = new BigDecimal(saleEntity.getStack());
        BigDecimal waste = new BigDecimal(saleEntity.getWaste());

        // Calculate saleAmount as (weight * stack) + waste
        BigDecimal saleAmount = weight.multiply(stack).add(waste);

        // Check if the subProduct has enough amount to fulfill the sale
        if (subProduct.getAmount().compareTo(saleAmount) < 0) {
            throw new InsufficientAmountException("Bu üründe yeterli miktar yok");
        }

        // Update product and subProduct amounts
        product.setTotalAmount(product.getTotalAmount().subtract(saleAmount));
        subProduct.setAmount(subProduct.getAmount().subtract(saleAmount));

        // Save updated product and subProduct entities
        productRepository.save(product);
        subProductRepository.save(subProduct);
    }
}
