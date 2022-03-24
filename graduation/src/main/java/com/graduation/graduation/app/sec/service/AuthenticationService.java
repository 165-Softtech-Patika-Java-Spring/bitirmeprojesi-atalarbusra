package com.graduation.graduation.app.sec.service;

import com.graduation.graduation.app.acc.dto.AccAccountDto;
import com.graduation.graduation.app.acc.dto.AccAccountSaveRequestDto;
import com.graduation.graduation.app.acc.entity.AccAccount;
import com.graduation.graduation.app.acc.service.AccAccountService;
import com.graduation.graduation.app.acc.service.entityservice.AccAccountEntityService;
import com.graduation.graduation.app.pro.service.ProProductService;
import com.graduation.graduation.app.sec.dto.SecLoginRequestDto;
import com.graduation.graduation.app.sec.enums.EnumJwtConstant;
import com.graduation.graduation.app.sec.security.JwtTokenGenerator;
import com.graduation.graduation.app.sec.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccAccountService accAccountService;
    private final AccAccountEntityService accAccountEntityService;
    private final ProProductService proProductService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenGenerator jwtTokenGenerator;

    public AccAccountDto  register(AccAccountSaveRequestDto accAccountSaveRequestDto) {

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return accAccountDto;
    }

    public String login(SecLoginRequestDto secLoginRequestDto) {


        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(secLoginRequestDto.getUserName(), secLoginRequestDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenGenerator.generateJwtToken(authentication);

        String bearer = EnumJwtConstant.BEARER.getConstant();

        proProductService.createAllProductType();

        return bearer + token;
    }

    public AccAccount getAccAccount() {

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        AccAccount accAccount = null;
        if (jwtUserDetails != null){
            accAccount = accAccountEntityService.getByIdWithControl(jwtUserDetails.getId());
        }

        return accAccount;
    }

    public Long getCurrentAccountId(){

        JwtUserDetails jwtUserDetails = getCurrentJwtUserDetails();

        Long jwtUserDetailsId = null;
        if (jwtUserDetails != null){
            jwtUserDetailsId = jwtUserDetails.getId();
        }

        return jwtUserDetailsId;
    }

    private JwtUserDetails getCurrentJwtUserDetails() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtUserDetails jwtUserDetails = null;
        if (authentication != null && authentication.getPrincipal() instanceof JwtUserDetails){
            jwtUserDetails = (JwtUserDetails) authentication.getPrincipal();
        }
        return jwtUserDetails;
    }
}
