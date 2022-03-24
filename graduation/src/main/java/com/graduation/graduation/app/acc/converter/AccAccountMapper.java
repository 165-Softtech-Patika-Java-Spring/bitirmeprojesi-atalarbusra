package com.graduation.graduation.app.acc.converter;

import com.graduation.graduation.app.acc.dto.AccAccountDto;
import com.graduation.graduation.app.acc.dto.AccAccountSaveRequestDto;
import com.graduation.graduation.app.acc.entity.AccAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccAccountMapper {

    AccAccountMapper INSTANCE = Mappers.getMapper(AccAccountMapper.class);

    List<AccAccountDto> convertToAccAccountDtoList(List<AccAccount> accAccountList);

    AccAccountDto convertToAccAccountDto(AccAccount accAccount);

    AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);

    AccAccount convertAccAccountDtoToAccAccount(AccAccountDto accAccountDto);
}
