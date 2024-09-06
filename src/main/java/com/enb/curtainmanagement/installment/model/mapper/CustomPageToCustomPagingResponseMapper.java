package com.enb.curtainmanagement.installment.model.mapper;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.common.model.dto.response.CustomPagingResponse;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.response.InstallmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CustomPageToCustomPagingResponseMapper {

    InstallmentToInstallmentResponseMapper INSTALLMENT_TO_INSTALLMENT_RESPONSE_MAPPER = Mappers.getMapper(InstallmentToInstallmentResponseMapper.class);

    default CustomPagingResponse<InstallmentResponse> toPagingResponse(CustomPage<Installment> installmentPage) {

        if (installmentPage == null) {
            return null;
        }

        return CustomPagingResponse.<InstallmentResponse>builder()
                .content(toInstallmentResponseList(installmentPage.getContent()))
                .totalElementCount(installmentPage.getTotalElementCount())
                .totalPageCount(installmentPage.getTotalPageCount())
                .pageNumber(installmentPage.getPageNumber())
                .pageSize(installmentPage.getPageSize())
                .build();

    }

    default List<InstallmentResponse> toInstallmentResponseList(List<Installment> installments) {

        if (installments == null) {
            return null;
        }

        return installments.stream()
                .map(INSTALLMENT_TO_INSTALLMENT_RESPONSE_MAPPER::map)
                .collect(Collectors.toList());

    }

    static CustomPageToCustomPagingResponseMapper initialize() {
        return Mappers.getMapper(CustomPageToCustomPagingResponseMapper.class);
    }

}
