package com.enb.curtainmanagement.admin.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.admin.model.Admin;
import com.enb.curtainmanagement.admin.model.entity.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminToAdminEntityMapper extends BaseMapper<Admin, AdminEntity> {

    @Override
    AdminEntity map(Admin source);

    static AdminToAdminEntityMapper initialize() {
        return Mappers.getMapper(AdminToAdminEntityMapper.class);
    }

}
