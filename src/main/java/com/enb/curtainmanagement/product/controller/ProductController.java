package com.enb.curtainmanagement.product.controller;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.common.model.dto.response.CustomResponse;
import com.enb.curtainmanagement.product.model.Product;
import com.enb.curtainmanagement.product.model.dto.request.ProductCreateRequest;
import com.enb.curtainmanagement.product.model.dto.request.ProductPagingRequest;
import com.enb.curtainmanagement.product.model.dto.request.ProductSearchRequest;
import com.enb.curtainmanagement.product.model.dto.request.ProductUpdateRequest;
import com.enb.curtainmanagement.product.model.dto.response.ProductResponse;
import com.enb.curtainmanagement.product.model.dto.response.ProductSearchResponse;
import com.enb.curtainmanagement.product.model.mapper.CustomPageToCustomPagingResponseMapper;
import com.enb.curtainmanagement.product.model.mapper.ProductToProductResponseMapper;
import com.enb.curtainmanagement.product.service.ProductCreateService;
import com.enb.curtainmanagement.product.service.ProductDeleteService;
import com.enb.curtainmanagement.product.service.ProductReadService;
import com.enb.curtainmanagement.product.service.ProductUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Validated
public class ProductController {

    private final ProductCreateService productCreateService;
    private final ProductReadService productReadService;
    private final ProductUpdateService productUpdateService;
    private final ProductDeleteService productDeleteService;

    private final ProductToProductResponseMapper productToProductResponseMapper = ProductToProductResponseMapper.initialize();

    private final CustomPageToCustomPagingResponseMapper customPageToCustomPagingResponseMapper =
            CustomPageToCustomPagingResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<String> createProduct(@RequestBody @Valid final ProductCreateRequest productCreateRequest) {

        final Product createdProduct = productCreateService
                .createProduct(productCreateRequest);

        return CustomResponse.successOf(createdProduct.getId());
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<ProductResponse> getProductById(@PathVariable @UUID final String productId) {

        final Product product = productReadService.getProductById(productId);

        final ProductResponse productResponse = productToProductResponseMapper.map(product);

        return CustomResponse.successOf(productResponse);

    }

    @PostMapping("/allproduct")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<ProductResponse>> getProducts(
            @RequestBody @Valid final ProductPagingRequest productPagingRequest) {

        final CustomPage<Product> productPage = productReadService.getProducts(productPagingRequest);

        final CustomPagingResponse<ProductResponse> productPagingResponse =
                customPageToCustomPagingResponseMapper.toPagingResponse(productPage);

        return CustomResponse.successOf(productPagingResponse);

    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<ProductResponse> updatedProductById(
            @RequestBody @Valid final ProductUpdateRequest productUpdateRequest,
            @PathVariable @UUID final String productId) {

        final Product updatedProduct = productUpdateService.updateProductById(productId, productUpdateRequest);

        final ProductResponse productResponse = productToProductResponseMapper.map(updatedProduct);

        return CustomResponse.successOf(productResponse);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> deleteProductById(@PathVariable @UUID final String productId) {

        productDeleteService.deleteProductById(productId);
        return CustomResponse.SUCCESS;
    }
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<ProductSearchResponse> searchProduct(@RequestBody @Valid final ProductSearchRequest searchRequest) {
        return productReadService.SearchProducts(searchRequest);
    }
}
