package com.graduation.graduation.app.pro.converter;

import com.graduation.graduation.app.pro.dto.ProProductDto;
import com.graduation.graduation.app.pro.dto.ProProductSaveRequestDto;
import com.graduation.graduation.app.pro.dto.ProProductTypeDto;
import com.graduation.graduation.app.pro.entity.ProProduct;
import com.graduation.graduation.app.pro.entity.ProProductType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProProductMapper {

    ProProductMapper INSTANCE = Mappers.getMapper(ProProductMapper.class);

    List<ProProduct> convertToProProductList(List<ProProductDto> proProductDtoList);

    List<ProProductDto> convertToProProductDtoList(List<ProProduct> proProductList);

    ProProduct convertToProProduct(ProProductDto proProductDto);

    ProProduct convertToProProduct(ProProductSaveRequestDto proProductSaveRequestDto);

    ProProductSaveRequestDto convertToProProductSaveRequestDto(ProProductDto proProductDto);

    ProProductDto convertToProductDto(ProProduct proProduct);

    List<ProProductType> convertToProProductTypeList(List<ProProductTypeDto> proProductTypeDtoList);

    List<ProProductTypeDto> convertToProProductTypeDtoList(List<ProProductType> proProductTypeList);

    ProProductType convertToProProductType(ProProductTypeDto proProductTypeDto);

    ProProductTypeDto convertToProProductTypeDto(ProProductType proProductType);

    //ProProductType convertToProProduct(ProProductSaveRequestDto proProductSaveRequestDto);
}
