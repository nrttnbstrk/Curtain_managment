package com.enb.curtainmanagement.installment.service;

import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentCreateRequest;

public interface InstallmentCreateService {

    Installment createInstallment(final InstallmentCreateRequest installmentCreateRequest);

}
