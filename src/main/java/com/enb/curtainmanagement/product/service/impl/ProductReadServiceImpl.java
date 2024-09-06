package com.enb.curtainmanagement.product.service.impl;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.service.ProductReadService;
import com.enb.curtainmanagement.product.specification.ProductSpecification;
import com.enb.curtainmanagement.product.model.dto.request.ProductSearchRequest;
import com.enb.curtainmanagement.product.model.dto.response.ProductSearchResponse;
import com.enb.curtainmanagement.product.model.entity.ProductEntity;
import com.enb.curtainmanagement.product.exception.ProductNotFoundException;
import com.enb.curtainmanagement.product.model.dto.request.ProductPagingRequest;
import com.enb.curtainmanagement.product.model.mapper.ListProductEntityToListProductMapper;
import com.enb.curtainmanagement.product.model.mapper.ProductEntityToProductMapper;
import com.enb.curtainmanagement.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductReadServiceImpl implements ProductReadService {

    private final ProductRepository productRepository;

    private final ProductEntityToProductMapper productEntityToProductMapper = ProductEntityToProductMapper.initialize();

    private final ListProductEntityToListProductMapper listProductEntityToListProductMapper =
            ListProductEntityToListProductMapper.initialize();

    @Override
    public Product getProductById(String productId) {

        final ProductEntity productEntityFromDB = productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("Belirtilen ürün mevcut değil."));

        return productEntityToProductMapper.map(productEntityFromDB);
    }

    @Override
    public CustomPage<Product> getProducts(ProductPagingRequest productPagingRequest) {

        final Page<ProductEntity> productEntityPage = productRepository.findAll(productPagingRequest.toPageable());

        if (productEntityPage.getContent().isEmpty()) {
            throw new ProductNotFoundException("Herhangi bir Ürün bulunamadı");
        }

        final List<Product> productDomainModels = listProductEntityToListProductMapper
                .toProductList(productEntityPage.getContent());

        return CustomPage.of(productDomainModels, productEntityPage);

    }
    @Override
    public List<ProductSearchResponse> SearchProducts(ProductSearchRequest searchRequest) {
        Specification<ProductEntity> specification = Specification.where(null);

        if (searchRequest.getName() != null && !searchRequest.getName().isEmpty()) {
            specification = specification.and(ProductSpecification.hasName(searchRequest.getName()));
        }
        if (searchRequest.getBrand() != null && !searchRequest.getBrand().isEmpty()) {
            specification = specification.and(ProductSpecification.hasBrand(searchRequest.getBrand()));
        }
        if (searchRequest.getCode() != null && !searchRequest.getCode().isEmpty()) {
            specification = specification.and(ProductSpecification.hasCode(searchRequest.getCode()));
        }
        if (searchRequest.getBarcode() != null && !searchRequest.getBarcode().isEmpty()) {
            specification = specification.and(ProductSpecification.hasBarcode(searchRequest.getBarcode()));
        }
        if (searchRequest.getSupplier() != null && !searchRequest.getSupplier().isEmpty()) {
            specification = specification.and(ProductSpecification.hasSupplier(searchRequest.getSupplier()));
        }

        List<ProductEntity> productEntities = productRepository.findAll(specification);

        return productEntities.stream().map(this::mapToResponse).collect(Collectors.toList());
    }


    private ProductSearchResponse mapToResponse(ProductEntity entity) {
        ProductSearchResponse response = new ProductSearchResponse();
        response.setName(entity.getName());
        response.setCode(entity.getCode());
        response.setBrand(entity.getBrand());
        response.setBarcode(entity.getBarcode());
        response.setSupplier(entity.getSupplier());
        return response;
    }

}
