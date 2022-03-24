package com.graduation.graduation.app.acc.controller;

import com.graduation.graduation.app.acc.dto.*;
import com.graduation.graduation.app.acc.service.AccAccountService;
import com.graduation.graduation.app.gen.dto.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccAccountController {

    private final AccAccountService accAccountService;

    @Operation(tags = "Account Controller")
    @GetMapping
    public ResponseEntity getAll(){

        List<AccAccountDto> accAccountDtoList = accAccountService.findAll();

        return ResponseEntity.ok(RestResponse.of(accAccountDtoList));
    }

    @Operation(tags = "Account Controller")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){

        AccAccountDto accAccountDto = accAccountService.findById(id);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }

    @Operation(tags = "Account Controller")
    @PostMapping
    public ResponseEntity save(@RequestBody AccAccountSaveRequestDto accAccountSaveRequestDto){

        AccAccountDto accAccountDto = accAccountService.save(accAccountSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }


    @Operation(tags = "Account Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){

        accAccountService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Account Controller")
    @PutMapping
    public ResponseEntity update(@RequestBody AccAccountDto accAccountDto){

        accAccountDto = accAccountService.update(accAccountDto);

        return ResponseEntity.ok(RestResponse.of(accAccountDto));
    }
}
