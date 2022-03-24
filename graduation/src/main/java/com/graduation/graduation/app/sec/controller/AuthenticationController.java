package com.graduation.graduation.app.sec.controller;

import com.graduation.graduation.app.acc.dto.AccAccountDto;
import com.graduation.graduation.app.acc.dto.AccAccountSaveRequestDto;
import com.graduation.graduation.app.gen.dto.RestResponse;
import com.graduation.graduation.app.sec.dto.SecLoginRequestDto;
import com.graduation.graduation.app.sec.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(tags = "Authentication Controller")
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody SecLoginRequestDto secLoginRequestDto){

        String token = authenticationService.login(secLoginRequestDto);

        return ResponseEntity.ok(RestResponse.of(token));
    }

    @Operation(tags = "Authentication Controller")
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto =authenticationService.register(accAccountSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }
}
