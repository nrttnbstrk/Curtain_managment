package com.enb.curtainmanagement.installment.service;

import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.request.InstallmentUpdateRequest;

public interface InstallmentUpdateService {

    Installment updateInstallmentById(final String installmentId, final InstallmentUpdateRequest installmentUpdateRequest);

}
