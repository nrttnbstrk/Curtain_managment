package com.enb.curtainmanagement.subProduct.controller;
import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.common.model.dto.response.CustomResponse;
import com.enb.curtainmanagement.subProduct.model.SubProduct;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductCreateRequest;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductPagingRequest;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductSearchRequest;
import com.enb.curtainmanagement.subProduct.model.dto.request.SubProductUpdateRequest;
import com.enb.curtainmanagement.subProduct.model.dto.response.SubProductResponse;
import com.enb.curtainmanagement.subProduct.model.dto.response.SubProductSearchResponse;
import com.enb.curtainmanagement.subProduct.model.mapper.CustomPageToCustomPagingResponseMapper;
import com.enb.curtainmanagement.subProduct.model.mapper.SubProductToSubProductResponseMapper;
import com.enb.curtainmanagement.subProduct.service.SubProductCreateService;
import com.enb.curtainmanagement.subProduct.service.SubProductDeleteService;
import com.enb.curtainmanagement.subProduct.service.SubProductReadService;
import com.enb.curtainmanagement.subProduct.service.SubProductUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/subProducts")
@RequiredArgsConstructor
@Validated
public class SubProductController {

    private final SubProductCreateService subProductCreateService;
    private final SubProductReadService subProductReadService;
    private final SubProductUpdateService subProductUpdateService;
    private final SubProductDeleteService subProductDeleteService;

    private final SubProductToSubProductResponseMapper subProductToSubProductResponseMapper = SubProductToSubProductResponseMapper.initialize();

    private final CustomPageToCustomPagingResponseMapper customPageToCustomPagingResponseMapper =
            CustomPageToCustomPagingResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<String> createProduct(@RequestBody @Valid final SubProductCreateRequest subProductCreateRequest) {

        final SubProduct createdSubProduct = subProductCreateService
                .createProduct(subProductCreateRequest);

        return CustomResponse.successOf(createdSubProduct.getId());
    }

    @GetMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<SubProductResponse> getProductById(@PathVariable @UUID final String productId) {

        final SubProduct subProduct = subProductReadService.getProductById(productId);

        final SubProductResponse subProductResponse = subProductToSubProductResponseMapper.map(subProduct);

        return CustomResponse.successOf(subProductResponse);

    }

    @PostMapping("/allproduct")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<SubProductResponse>> getProducts(
            @RequestBody @Valid final SubProductPagingRequest subProductPagingRequest) {

        final CustomPage<SubProduct> productPage = subProductReadService.getProducts(subProductPagingRequest);

        final CustomPagingResponse<SubProductResponse> productPagingResponse =
                customPageToCustomPagingResponseMapper.toPagingResponse(productPage);

        return CustomResponse.successOf(productPagingResponse);

    }

    @PutMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<SubProductResponse> updatedProductById(
            @RequestBody @Valid final SubProductUpdateRequest subProductUpdateRequest,
            @PathVariable @UUID final String productId) {

        final SubProduct updatedSubProduct = subProductUpdateService.updateProductById(productId, subProductUpdateRequest);

        final SubProductResponse subProductResponse = subProductToSubProductResponseMapper.map(updatedSubProduct);

        return CustomResponse.successOf(subProductResponse);
    }

    @DeleteMapping("/{productId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> deleteProductById(@PathVariable @UUID final String productId) {

        subProductDeleteService.deleteProductById(productId);
        return CustomResponse.SUCCESS;
    }
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<SubProductSearchResponse> searchProduct(@RequestBody @Valid final SubProductSearchRequest searchRequest) {
        return subProductReadService.SearchProducts(searchRequest);
    }
}
