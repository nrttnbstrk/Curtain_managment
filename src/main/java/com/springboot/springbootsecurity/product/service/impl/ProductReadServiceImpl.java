package com.springboot.springbootsecurity.product.service.impl;

import com.springboot.springbootsecurity.common.model.CustomPage;
import com.springboot.springbootsecurity.product.model.dto.request.ProductSearchRequest;
import com.springboot.springbootsecurity.product.model.dto.response.ProductSearchResponse;
import com.springboot.springbootsecurity.product.model.entity.ProductEntity;
import com.springboot.springbootsecurity.product.exception.ProductNotFoundException;
import com.springboot.springbootsecurity.product.model.Product;
import com.springboot.springbootsecurity.product.model.dto.request.ProductPagingRequest;
import com.springboot.springbootsecurity.product.model.mapper.ListProductEntityToListProductMapper;
import com.springboot.springbootsecurity.product.model.mapper.ProductEntityToProductMapper;
import com.springboot.springbootsecurity.product.repository.ProductRepository;
import com.springboot.springbootsecurity.product.service.ProductReadService;
import com.springboot.springbootsecurity.product.specification.ProductSpecification;
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
                .orElseThrow(() -> new ProductNotFoundException("Verilen product ID ile = " + productId));

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
