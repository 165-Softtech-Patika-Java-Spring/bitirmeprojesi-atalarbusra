package com.graduation.graduation.app.pro.controller;

import com.graduation.graduation.app.gen.dto.RestResponse;
import com.graduation.graduation.app.pro.dto.ProProductTypeDto;
import com.graduation.graduation.app.pro.dto.ProProductTypeUpdateDto;
import com.graduation.graduation.app.pro.service.ProProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/productTypes")
@RequiredArgsConstructor
public class ProProductTypeController {

    private final ProProductService proProductService;

    @Operation(tags = "Product Controller")
    @GetMapping
    public ResponseEntity getAll() {

        List<ProProductTypeDto> proProductTypeDtoList = proProductService.findAllInType();

        return ResponseEntity.ok(RestResponse.of(proProductTypeDtoList));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {

        ProProductTypeDto proProductTypeDto = proProductService.findByIdReturnDto(id);

        return ResponseEntity.ok(RestResponse.of(proProductTypeDto));
    }

    @Operation(tags = "Product Controller")
    @PutMapping
    public ResponseEntity update(@RequestBody ProProductTypeUpdateDto proProductTypeUpdateDto) {

        ProProductTypeDto proProductTypeDto = proProductService.update(proProductTypeUpdateDto);

        return ResponseEntity.ok(RestResponse.of(proProductTypeDto));
    }
}
