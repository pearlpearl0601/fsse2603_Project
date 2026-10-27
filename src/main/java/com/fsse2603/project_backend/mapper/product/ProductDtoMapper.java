package com.fsse2603.project_backend.mapper.product;

import com.fsse2603.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2603.project_backend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2603.project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2603.project_backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDtoMapper{
    public GetAllProductResponseDto toGetAllProductResponseDto (ProductResponseData productResponseData){
        GetAllProductResponseDto getAllProductResponseDto = new GetAllProductResponseDto();
        getAllProductResponseDto.setPid(productResponseData.getPid());
        getAllProductResponseDto.setName(productResponseData.getName());
        getAllProductResponseDto.setImageUrl(productResponseData.getImageUrl());
        getAllProductResponseDto.setPrice(productResponseData.getPrice());
        getAllProductResponseDto.setHasStock(productResponseData.getStock() >0);

        return getAllProductResponseDto;
    }

    public List<GetAllProductResponseDto> toGetAllProductResponseDtoList(List<ProductResponseData> productResponseDataList){
        List<GetAllProductResponseDto> getAllProductResponseDtoList = new ArrayList<>();
        for(ProductResponseData productResponseData: productResponseDataList){
            getAllProductResponseDtoList.add(toGetAllProductResponseDto(productResponseData));
        }
        return getAllProductResponseDtoList;
    }

    public ProductResponseDto toProductResponseDto (ProductResponseData productResponseData){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPid(productResponseData.getPid());
        productResponseDto.setName(productResponseData.getName());
        productResponseDto.setDescription(productResponseData.getDescription());
        productResponseDto.setImageUrl(productResponseData.getImageUrl());
        productResponseDto.setPrice(productResponseData.getPrice());
        productResponseDto.setStock(productResponseData.getStock());

        return productResponseDto;
    }

    public ProductResponseDto toProductResponseDto (TransactionProductResponseData transactionProductResponseData){
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setPid(transactionProductResponseData.getPid());
        productResponseDto.setName(transactionProductResponseData.getName());
        productResponseDto.setDescription(transactionProductResponseData.getDescription());
        productResponseDto.setImageUrl(transactionProductResponseData.getImageUrl());
        productResponseDto.setPrice(transactionProductResponseData.getPrice());
        productResponseDto.setStock(transactionProductResponseData.getStock());

        return productResponseDto;
    }

}

