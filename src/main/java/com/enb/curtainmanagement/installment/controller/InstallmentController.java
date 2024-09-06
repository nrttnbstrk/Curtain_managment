package com.enb.curtainmanagement.installment.controller;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.common.model.dto.response.CustomResponse;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentCreateRequest;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentPagingRequest;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentSearchRequest;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentUpdateRequest;
import com.enb.curtainmanagement.installment.model.dto.response.InstallmentResponse;
import com.enb.curtainmanagement.installment.model.dto.response.InstallmentSearchResponse;
import com.enb.curtainmanagement.installment.model.mapper.CustomPageToCustomPagingResponseMapper;
import com.enb.curtainmanagement.installment.model.mapper.InstallmentToInstallmentResponseMapper;
import com.enb.curtainmanagement.installment.service.InstallmentCreateService;
import com.enb.curtainmanagement.installment.service.InstallmentDeleteService;
import com.enb.curtainmanagement.installment.service.InstallmentReadService;
import com.enb.curtainmanagement.installment.service.InstallmentUpdateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.UUID;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/installment")
@RequiredArgsConstructor
@Validated
public class InstallmentController {

    private final InstallmentCreateService installmentCreateService;
    private final InstallmentReadService installmentReadService;
    private final InstallmentUpdateService installmentUpdateService;
    private final InstallmentDeleteService installmentDeleteService;

    private final InstallmentToInstallmentResponseMapper installmentToInstallmentResponseMapper = InstallmentToInstallmentResponseMapper.initialize();

    private final CustomPageToCustomPagingResponseMapper customPageToCustomPagingResponseMapper =
            CustomPageToCustomPagingResponseMapper.initialize();

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<String> createInstallment(@RequestBody @Valid final InstallmentCreateRequest installmentCreateRequest) {

        final Installment createdInstallment = installmentCreateService
                .createInstallment(installmentCreateRequest);

        return CustomResponse.successOf(createdInstallment.getId());
    }

    @GetMapping("/{installmentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<InstallmentResponse> getInstallmentById(@PathVariable @UUID final String installmentId) {

        final Installment installment = installmentReadService.getInstallmentById(installmentId);

        final InstallmentResponse installmentResponse = installmentToInstallmentResponseMapper.map(installment);

        return CustomResponse.successOf(installmentResponse);

    }



    @PutMapping("/{installmentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<InstallmentResponse> updatedInstallmentById(
            @RequestBody @Valid final InstallmentUpdateRequest installmentUpdateRequest,
            @PathVariable @UUID final String installmentId) {

        final Installment updatedInstallment = installmentUpdateService.updateInstallmentById(installmentId, installmentUpdateRequest);

        final InstallmentResponse installmentResponse = installmentToInstallmentResponseMapper.map(updatedInstallment);

        return CustomResponse.successOf(installmentResponse);
    }

    @PostMapping("/allInstallment")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<InstallmentResponse>> getInstallments(
            @RequestBody @Valid final InstallmentPagingRequest installmentPagingRequest) {

        final CustomPage<Installment> installmentPage = installmentReadService.getInstallments(installmentPagingRequest);

        final CustomPagingResponse<InstallmentResponse> installmentPagingResponse =
                customPageToCustomPagingResponseMapper.toPagingResponse(installmentPage);

        return CustomResponse.successOf(installmentPagingResponse);
    }



    @DeleteMapping("/{installmentId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<Void> deleteInstallmentById(@PathVariable @UUID final String installmentId) {

        installmentDeleteService.deleteInstallmentById(installmentId);
        return CustomResponse.SUCCESS;
    }
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public List<InstallmentSearchResponse> searchInstallments(@RequestBody @Valid final InstallmentSearchRequest searchRequest) {
        return installmentReadService.searchInstallments(searchRequest);
    }
}
