package com.enb.curtainmanagement.installment.service;

import com.enb.curtainmanagement.common.model.CustomPage;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentPagingRequest;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentSearchRequest;
import com.enb.curtainmanagement.installment.model.dto.response.InstallmentSearchResponse;
import com.enb.curtainmanagement.sale.model.Sale;

import java.util.List;

public interface InstallmentReadService {

    Installment getInstallmentById(final String productId);

    CustomPage<Installment> getInstallments(final InstallmentPagingRequest installmentPagingRequest);

    List<InstallmentSearchResponse> searchInstallments(InstallmentSearchRequest searchRequest);


}
