package com.enb.curtainmanagement.auth.model.mapper;

import com.enb.curtainmanagement.auth.model.Token;
import com.enb.curtainmanagement.common.model.mapper.BaseMapper;
import com.enb.curtainmanagement.auth.model.dto.response.TokenResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TokenToTokenResponseMapper extends BaseMapper<Token, TokenResponse> {

    @Override
    TokenResponse map(Token source);

    static TokenToTokenResponseMapper initialize() {
        return Mappers.getMapper(TokenToTokenResponseMapper.class);
    }

}
