package com.enb.curtainmanagement.sale.controller;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.common.model.dto.response.CustomResponse;
import com.enb.curtainmanagement.sale.model.Sale;
import com.enb.curtainmanagement.sale.model.dto.request.SaleCreateRequest;
import com.enb.curtainmanagement.sale.model.dto.request.SalePagingRequest;
import com.enb.curtainmanagement.sale.model.dto.request.SaleSearchRequest;
import com.enb.curtainmanagement.sale.model.dto.request.SaleUpdateRequest;
import com.enb.curtainmanagement.sale.model.dto.response.SaleResponse;
import com.enb.curtainmanagement.sale.model.dto.response.SaleSearchResponse;
import com.enb.curtainmanagement.sale.model.mapper.CustomPageToCustomPagingResponseMapper;
import com.enb.curtainmanagement.sale.model.mapper.SaleToSaleResponseMapper;
import com.enb.curtainmanagement.sale.service.SaleCreateService;
import com.enb.curtainmanagement.sale.service.SaleDeleteService;
import com.enb.curtainmanagement.sale.service.SaleReadService;
import com.enb.curtainmanagement.sale.service.SaleUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sale")
@RequiredArgsConstructor
@Validated
public class SaleController {

    private final SaleCreateService saleCreateService;
    private final SaleReadService saleReadService;
    private final SaleUpdateService saleUpdateService;
    private final SaleDeleteService saleDeleteService;

    private final SaleToSaleResponseMapper saleToSaleResponseMapper = SaleToSaleResponseMapper.initialize();

    private final CustomPageToCustomPagingResponseMapper customPageToCustomPagingResponseMapper =
            CustomPageToCustomPagingResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<String> createSale(@RequestBody @Valid final SaleCreateRequest saleCreateRequest) {

        final Sale createdSale = saleCreateService
                .createSale(saleCreateRequest);

        return CustomResponse.successOf(createdSale.getId());
    }

    @GetMapping("/{saleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<SaleResponse> getSaleById(@PathVariable @UUID final String saleId) {

        final Sale sale = saleReadService.getSaleById(saleId);

        final SaleResponse saleResponse = saleToSaleResponseMapper.map(sale);

        return CustomResponse.successOf(saleResponse);

    }

    @PostMapping("/allSale")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<SaleResponse>> getSales(
            @RequestBody @Valid final SalePagingRequest salePagingRequest) {

        final CustomPage<Sale> salePage = saleReadService.getSales(salePagingRequest);

        final CustomPagingResponse<SaleResponse> salePagingResponse =
                customPageToCustomPagingResponseMapper.toPagingResponse(salePage);

        return CustomResponse.successOf(salePagingResponse);

    }

    @PutMapping("/{saleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<SaleResponse> updatedSaleById(
            @RequestBody @Valid final SaleUpdateRequest saleUpdateRequest,
            @PathVariable @UUID final String saleId) {

        final Sale updatedSale = saleUpdateService.updateSaleById(saleId, saleUpdateRequest);

        final SaleResponse saleResponse = saleToSaleResponseMapper.map(updatedSale);

        return CustomResponse.successOf(saleResponse);
    }

    @DeleteMapping("/{saleId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> deleteSaleById(@PathVariable @UUID final String saleId) {

        saleDeleteService.deleteSaleById(saleId);
        return CustomResponse.SUCCESS;
    }
    @GetMapping("/customer/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<List<SaleResponse>> getSalesByCustomerId(@PathVariable @UUID final String customerId) {

        final List<Sale> sales = saleReadService.getSalesByCustomerId(customerId);

        final List<SaleResponse> saleResponses = sales.stream()
                .map(saleToSaleResponseMapper::map)
                .toList();

        return CustomResponse.successOf(saleResponses);
    }
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<SaleSearchResponse> searchSale(@RequestBody @Valid final SaleSearchRequest searchRequest) {
        return saleReadService.SearchSale(searchRequest);
    }
}
