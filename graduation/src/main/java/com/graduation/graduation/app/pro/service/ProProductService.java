package com.graduation.graduation.app.pro.service;

import com.graduation.graduation.app.gen.enums.GenErrorMessage;
import com.graduation.graduation.app.gen.exceptions.GenBusinessException;
import com.graduation.graduation.app.pro.converter.ProProductMapper;
import com.graduation.graduation.app.pro.dto.*;
import com.graduation.graduation.app.pro.entity.ProProduct;
import com.graduation.graduation.app.pro.entity.ProProductType;
import com.graduation.graduation.app.pro.service.entityService.ProProductEntityService;
import com.graduation.graduation.app.pro.service.entityService.ProProductTypeEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProProductService {

    private final ProProductEntityService proProductEntityService;

    private final ProProductTypeEntityService proProductTypeEntityService;

    public List<ProProductDto> findAll() {

        List<ProProduct> proProductList = proProductEntityService.findAll();

        List<ProProductDto> proProductDtoList = ProProductMapper.INSTANCE.convertToProProductDtoList(proProductList);

        return proProductDtoList;
    }

    public ProProductDto findById(Long id) {

        ProProduct proProduct = proProductEntityService.getByIdWithControl(id);

        ProProductDto proProductDto = ProProductMapper.INSTANCE.convertToProductDto(proProduct);

        return proProductDto;
    }
    public List<ProProductDto> findProductByTypeId(Long id) {

        List<ProProduct> proProductList = proProductEntityService.findProductByTypeId(id);

        List<ProProductDto> proProductDtoList = ProProductMapper.INSTANCE.convertToProProductDtoList(proProductList);

        return proProductDtoList;
    }
    public List<ProProductDto> findProductWithPrice(double minPrice,double maxPrice) {

        List<ProProduct> proProductList = proProductEntityService.findProductWithPrice(minPrice, maxPrice);

        List<ProProductDto> proProductDtoList = ProProductMapper.INSTANCE.convertToProProductDtoList(proProductList);

        return proProductDtoList;
    }

    public List<ProProductDetails> getProductDetails(){
        return proProductEntityService.getProductDetails();
    }

    public ProProductDto save(ProProductSaveRequestDto proProductSaveRequestDto) {

        proProductSaveControl(proProductSaveRequestDto);
        ProProduct proProduct = ProProductMapper.INSTANCE.convertToProProduct(proProductSaveRequestDto);
        ProProductType proProductType = proProductTypeEntityService.findById(proProduct.getProductTypeId()).get();
        proProduct.setPriceWithKdv(proProduct.getPrice() * (1.0 + (proProductType.getKdvRate() / 100.0)));
        proProduct = proProductEntityService.save(proProduct);

        ProProductDto proProductDto = ProProductMapper.INSTANCE.convertToProductDto(proProduct);

        return proProductDto;
    }

    public void delete(Long id) {
        Optional<ProProduct> proProduct = proProductEntityService.findById(id);
        proProductEntityService.delete(proProduct.get());
    }

    public ProProductDto update(ProProductDto proProductDto) {

        proProductSaveControl(ProProductMapper.INSTANCE.convertToProProductSaveRequestDto(proProductDto));

        ProProduct proProduct = ProProductMapper.INSTANCE.convertToProProduct(proProductDto);
        ProProductType proProductType = proProductTypeEntityService.findById(proProduct.getProductTypeId()).get();
        proProduct.setPriceWithKdv(proProduct.getPrice() * (1.0 + (proProductType.getKdvRate() / 100.0)));
        proProduct = proProductEntityService.save(proProduct);

        proProductDto = ProProductMapper.INSTANCE.convertToProductDto(proProduct);

        return proProductDto;
    }

    public ProProductDto updatePrice(ProProductUpdatePriceDto proProductUpdatePriceDto){

        ProProduct proProduct = proProductEntityService.findById(proProductUpdatePriceDto.getProductId()).get();

        proProduct.setPrice(proProductUpdatePriceDto.getNewPrice());
        ProProductType proProductType = proProductTypeEntityService.findById(proProduct.getProductTypeId()).get();
        proProduct.setPriceWithKdv(proProductUpdatePriceDto.getNewPrice() * (1.0 + (proProductType.getKdvRate() / 100.0)));
        proProduct = proProductEntityService.save(proProduct);

        ProProductDto proProductDto = ProProductMapper.INSTANCE.convertToProductDto(proProduct);

        return proProductDto;
    }

    private void proProductSaveControl(ProProductSaveRequestDto dto) {
        if (dto.getProductTypeId() == null) {
            throw new GenBusinessException(GenErrorMessage.valueOf("Product Type is null"));
        }
        if (dto.getAmount() == null) {
            throw new GenBusinessException(GenErrorMessage.valueOf("Amount is null"));
        }
        if (dto.getName() == null) {
            throw new GenBusinessException(GenErrorMessage.valueOf("Name is null"));
        }
        if (dto.getPrice() == null) {
            throw new GenBusinessException(GenErrorMessage.valueOf("Price is null"));
        }

        if (dto.getPrice() == 0.0)
            throw new GenBusinessException(GenErrorMessage.valueOf("Price entered as zero"));

        if (dto.getPrice() < 0)
            throw new GenBusinessException(GenErrorMessage.valueOf("Price entered less than zero"));
    }


    public void createAllProductType() {
        List<ProProductType> proProductTypeList = proProductTypeEntityService.findAll();
        if (proProductTypeList.size() != 6) {
            proProductTypeEntityService.deleteAll();

            ProProductType proProductType;
            proProductType = new ProProductType(1L, "Food", 1L);
            proProductTypeEntityService.save(proProductType);
            proProductType = new ProProductType(2L, "Stationery", 8L);
            proProductTypeEntityService.save(proProductType);
            proProductType = new ProProductType(3L, "Clothing", 8L);
            proProductTypeEntityService.save(proProductType);
            proProductType = new ProProductType(4L, "Technology", 18L);
            proProductTypeEntityService.save(proProductType);
            proProductType = new ProProductType(5L, "Cleaning", 18L);
            proProductTypeEntityService.save(proProductType);
            proProductType = new ProProductType(6L, "Other", 18L);
            proProductTypeEntityService.save(proProductType);
        }
    }

    ProProductType findByIdInType(Long id) {
        return proProductTypeEntityService.getByIdWithControl(id);
    }

    public List<ProProductTypeDto> findAllInType() {

        List<ProProductType> proProductTypeList = proProductTypeEntityService.findAll();

        List<ProProductTypeDto> proProductTypeDtoList = ProProductMapper.INSTANCE.convertToProProductTypeDtoList(proProductTypeList);

        return proProductTypeDtoList;
    }

    public ProProductTypeDto findByIdReturnDto(Long id) {

        ProProductType proProductType = proProductTypeEntityService.getByIdWithControl(id);

        ProProductTypeDto proProductTypeDto = ProProductMapper.INSTANCE.convertToProProductTypeDto(proProductType);

        return proProductTypeDto;
    }

    public ProProductTypeDto update(ProProductTypeUpdateDto proProductTypeUpdateDto) {

        if(proProductTypeUpdateDto.getKdvRate() < 0)
            throw new GenBusinessException(GenErrorMessage.valueOf("KDV rate entered less than zero"));
        ProProductType proProductType = findByIdInType(proProductTypeUpdateDto.getId());
        proProductType.setKdvRate(proProductTypeUpdateDto.getKdvRate());
        proProductTypeEntityService.save(proProductType);
        List<ProProductDto> proProductList = findProductByTypeId(proProductTypeUpdateDto.getId());

        for (ProProductDto proProductDto : proProductList) {
            update(proProductDto);
        }
        ProProductTypeDto proProductTypeDto = ProProductMapper.INSTANCE.convertToProProductTypeDto(proProductType);
        return proProductTypeDto;
    }
}
