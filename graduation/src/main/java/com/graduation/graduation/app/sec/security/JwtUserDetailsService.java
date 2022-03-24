package com.graduation.graduation.app.sec.security;

import com.graduation.graduation.app.acc.entity.AccAccount;
import com.graduation.graduation.app.acc.service.entityservice.AccAccountEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final AccAccountEntityService accAccountEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        AccAccount accAccount = accAccountEntityService.findByUserName(username);

        return JwtUserDetails.create(accAccount);
    }

    public UserDetails loadUserByUserId(Long id) {

        AccAccount accAccount = accAccountEntityService.getByIdWithControl(id);

        return JwtUserDetails.create(accAccount);
    }
}
