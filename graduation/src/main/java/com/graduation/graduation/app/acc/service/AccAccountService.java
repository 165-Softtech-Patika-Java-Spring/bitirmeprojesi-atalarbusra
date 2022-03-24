package com.graduation.graduation.app.acc.service;

import com.graduation.graduation.app.acc.converter.AccAccountMapper;
import com.graduation.graduation.app.acc.dto.AccAccountDto;
import com.graduation.graduation.app.acc.dto.AccAccountSaveRequestDto;
import com.graduation.graduation.app.acc.entity.AccAccount;
import com.graduation.graduation.app.acc.service.entityservice.AccAccountEntityService;
import com.graduation.graduation.app.gen.enums.GenErrorMessage;
import com.graduation.graduation.app.gen.exceptions.GenBusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AccAccountService {

    private final AccAccountEntityService accAccountEntityService;

    private final PasswordEncoder passwordEncoder;

    public List<AccAccountDto> findAll() {

        List<AccAccount> accAccountList = accAccountEntityService.findAll();

        List<AccAccountDto> accAccountDtoList = AccAccountMapper.INSTANCE.convertToAccAccountDtoList(accAccountList);

        return accAccountDtoList;
    }

    public AccAccountDto findById(Long id) {

        AccAccount accAccount = accAccountEntityService.getByIdWithControl(id);

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    public AccAccountDto save(AccAccountSaveRequestDto accAccountSaveRequestDto) {
        AccAccount accAccount;
        accAccount = accAccountEntityService.findByUserName(accAccountSaveRequestDto.getUserName());
        if(accAccount != null){
            throw new GenBusinessException(GenErrorMessage.valueOf("User name is exist"));
        }

        accAccount = AccAccountMapper.INSTANCE.convertToAccAccount(accAccountSaveRequestDto);
        String password = passwordEncoder.encode(accAccount.getPassword());
        accAccount.setPassword(password);
        accAccount = accAccountEntityService.save(accAccount);

        AccAccountDto accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

    public void delete(Long id){
        Optional<AccAccount> accAccount = accAccountEntityService.findById(id);
        accAccountEntityService.delete(accAccount.get());
    }

    public AccAccountDto update(AccAccountDto accAccountDto){

        AccAccount accAccount = AccAccountMapper.INSTANCE.convertAccAccountDtoToAccAccount(accAccountDto);
        String password = passwordEncoder.encode(accAccount.getPassword());
        accAccount.setPassword(password);
        accAccount = accAccountEntityService.save(accAccount);

        accAccountDto = AccAccountMapper.INSTANCE.convertToAccAccountDto(accAccount);

        return accAccountDto;
    }

}
