package com.enb.curtainmanagement.installment.model.mapper;

import com.enb.curtainmanagement.installment.model.Installment;
import com.enb.curtainmanagement.installment.model.entity.InstallmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ListInstallmentEntityToListInstallmentMapper {

    InstallmentEntityToInstallmentMapper INSTALLMENT_ENTITY_TO_INSTALLMENT_MAPPER = Mappers.getMapper(InstallmentEntityToInstallmentMapper.class);

    default List<Installment> toInstallmentList(List<InstallmentEntity> installmentEntities) {

        if (installmentEntities == null) {
            return null;
        }

        return installmentEntities.stream()
                .map(INSTALLMENT_ENTITY_TO_INSTALLMENT_MAPPER::map)
                .collect(Collectors.toList());

    }


    static ListInstallmentEntityToListInstallmentMapper initialize() {
        return Mappers.getMapper(ListInstallmentEntityToListInstallmentMapper.class);
    }

}
