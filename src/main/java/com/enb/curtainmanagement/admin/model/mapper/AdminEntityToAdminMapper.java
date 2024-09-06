package com.enb.curtainmanagement.admin.model.mapper;

import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.admin.model.Admin;
import com.enb.curtainmanagement.admin.model.entity.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminEntityToAdminMapper extends BaseMapper<AdminEntity, Admin> {

    @Override
    Admin map(AdminEntity source);

    static AdminEntityToAdminMapper initialize() {
        return Mappers.getMapper(AdminEntityToAdminMapper.class);
    }

}
