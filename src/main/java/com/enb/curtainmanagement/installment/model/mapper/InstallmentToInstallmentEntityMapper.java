package com.enb.curtainmanagement.installment.model.mapper;


import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InstallmentToInstallmentEntityMapper extends BaseMapper<Installment, InstallmentEntity> {

    @Override
    InstallmentEntity map(Installment source);

    static InstallmentToInstallmentEntityMapper initialize() {
        return Mappers.getMapper(InstallmentToInstallmentEntityMapper.class);
    }

}

