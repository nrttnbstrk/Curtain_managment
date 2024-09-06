package com.enb.curtainmanagement.installment.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.dto.response.InstallmentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstallmentToInstallmentResponseMapper extends BaseMapper<Installment, InstallmentResponse> {

    @Override
    InstallmentResponse map(Installment source);

    static InstallmentToInstallmentResponseMapper initialize() {
        return Mappers.getMapper(InstallmentToInstallmentResponseMapper.class);
    }

}
