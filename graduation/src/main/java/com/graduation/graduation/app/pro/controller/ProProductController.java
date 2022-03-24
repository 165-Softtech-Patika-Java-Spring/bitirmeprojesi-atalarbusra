package com.graduation.graduation.app.pro.controller;

import com.graduation.graduation.app.gen.dto.RestResponse;
import com.graduation.graduation.app.pro.dto.ProProductDetails;
import com.graduation.graduation.app.pro.dto.ProProductDto;
import com.graduation.graduation.app.pro.dto.ProProductSaveRequestDto;
import com.graduation.graduation.app.pro.dto.ProProductUpdatePriceDto;
import com.graduation.graduation.app.pro.service.ProProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProProductController {

    private final ProProductService proProductService;

    @Operation(tags = "Product Controller")
    @GetMapping
    public ResponseEntity getAll() {

        List<ProProductDto> proProductDtoList = proProductService.findAll();

        return ResponseEntity.ok(RestResponse.of(proProductDtoList));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {

        ProProductDto proProductDto = proProductService.findById(id);

        return ResponseEntity.ok(RestResponse.of(proProductDto));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/findProductByTypeId/{id}")
    public ResponseEntity findProductByTypeId(@PathVariable Long id) {

        List<ProProductDto> proProductDtoList = proProductService.findProductByTypeId(id);

        return ResponseEntity.ok(RestResponse.of(proProductDtoList));
    }

    @Operation(tags = "Product Controller")
    @GetMapping("/findProductWithPrice")
    public ResponseEntity findProductWithPrice(@RequestParam("minPrice") double minPrice, @RequestParam("maxPrice") double maxPrice) {

        List<ProProductDto> proProductDtoList = proProductService.findProductWithPrice(minPrice, maxPrice);

        return ResponseEntity.ok(RestResponse.of(proProductDtoList));
    }
    @Operation(tags = "Product Controller")
    @GetMapping("/getProductDetails")
    public ResponseEntity getProductDetails() {

        List<ProProductDetails> productDetailsList = proProductService.getProductDetails();

        return ResponseEntity.ok(RestResponse.of(productDetailsList));
    }

    @Operation(tags = "Product Controller")
    @PostMapping
    public ResponseEntity save(@RequestBody ProProductSaveRequestDto proProductSaveRequestDto) {

        ProProductDto proProductDto = proProductService.save(proProductSaveRequestDto);

        return ResponseEntity.ok(RestResponse.of(proProductDto));
    }

    @Operation(tags = "Product Controller")
    @PostMapping("/updatePrice")
    public ResponseEntity updatePrice(@RequestBody ProProductUpdatePriceDto proProductUpdatePriceDto) {

        ProProductDto proProductDto = proProductService.updatePrice(proProductUpdatePriceDto);

        return ResponseEntity.ok(RestResponse.of(proProductDto));
    }


    @Operation(tags = "Product Controller")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        proProductService.delete(id);

        return ResponseEntity.ok(RestResponse.empty());
    }

    @Operation(tags = "Product Controller")
    @PutMapping
    public ResponseEntity update(@RequestBody ProProductDto proProductDto) {

        proProductDto = proProductService.update(proProductDto);

        return ResponseEntity.ok(RestResponse.of(proProductDto));
    }
}
