package com.springboot.springbootsecurity.subProduct.service.impl;
import com.springboot.springbootsecurity.common.exception.GlobalExceptionHandler;
import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.subProduct.model.SubProduct;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductSearchRequest;
import com.springboot.springbootsecurity.subProduct.model.dto.response.SubProductSearchResponse;
import com.springboot.springbootsecurity.subProduct.model.entity.SubProductEntity;
import com.springboot.springbootsecurity.subProduct.exception.SubProductNotFoundException;
import com.springboot.springbootsecurity.subProduct.model.dto.request.SubProductPagingRequest;
import com.springboot.springbootsecurity.subProduct.model.mapper.ListSubProductEntityToListProductMapper;
import com.springboot.springbootsecurity.subProduct.model.mapper.SubProductEntityToSubProductMapper;
import com.springboot.springbootsecurity.subProduct.repository.SubProductRepository;
import com.springboot.springbootsecurity.subProduct.service.SubProductReadService;
import com.springboot.springbootsecurity.subProduct.specification.SubProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubProductReadServiceImpl implements SubProductReadService {

    private final SubProductRepository subProductRepository;

    private final SubProductEntityToSubProductMapper subProductEntityToSubProductMapper = SubProductEntityToSubProductMapper.initialize();

    private final ListSubProductEntityToListProductMapper listSubProductEntityToListProductMapper =
            ListSubProductEntityToListProductMapper.initialize();

    @Override
    public SubProduct getProductById(String productId) {

        final SubProductEntity subProductEntityFromDB = subProductRepository
                .findById(productId)
                .orElseThrow(() -> new SubProductNotFoundException("Belirtilen ALT URUN mevcut değil."));

        return subProductEntityToSubProductMapper.map(subProductEntityFromDB);
    }

    @Override
    public CustomPage<SubProduct> getProducts(SubProductPagingRequest subProductPagingRequest) {

        final Page<SubProductEntity> productEntityPage = subProductRepository.findAll(subProductPagingRequest.toPageable());

        if (productEntityPage.getContent().isEmpty()) {
            throw new SubProductNotFoundException("Herhangi bir ALT URUN bulunamadı");
        }

        final List<SubProduct> productDomainModels = listSubProductEntityToListProductMapper
                .toProductList(productEntityPage.getContent());

        return CustomPage.of(productDomainModels, productEntityPage);

    }
    @Override
    public List<SubProductSearchResponse> SearchProducts(SubProductSearchRequest searchRequest) {
        Specification<SubProductEntity> specification = Specification.where(null);

        if (searchRequest.getProductId() != null && !searchRequest.getProductId().isEmpty()) {
            specification = specification.and(SubProductSpecification.hasProductId(searchRequest.getProductId()));
        }
        if (searchRequest.getBarcode() != null && !searchRequest.getBarcode().isEmpty()) {
            specification = specification.and(SubProductSpecification.hasBarcode(searchRequest.getBarcode()));
        }
        if (searchRequest.getSupplier() != null && !searchRequest.getSupplier().isEmpty()) {
            specification = specification.and(SubProductSpecification.hasSupplier(searchRequest.getSupplier()));
        }

        List<SubProductEntity> productEntities = subProductRepository.findAll(specification);

        return productEntities.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private SubProductSearchResponse mapToResponse(SubProductEntity entity) {
        SubProductSearchResponse response = new SubProductSearchResponse();
        response.setId(entity.getId());
        response.setProductId(entity.getProductId());
        response.setBarcode(entity.getBarcode());
        response.setSupplier(entity.getSupplier());
        return response;
    }

}
