package com.fsse2603.project_backend.controller;

import com.fsse2603.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2603.project_backend.data.product.dto.response.GetAllProductResponseDto;
import com.fsse2603.project_backend.data.product.dto.response.ProductResponseDto;
import com.fsse2603.project_backend.mapper.product.ProductDtoMapper;
import com.fsse2603.project_backend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public/products")
public class ProductController{
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }


    @GetMapping()  //link to /public/products
    public List<GetAllProductResponseDto> getAllProduct(){
        List<ProductResponseData> productResponseDataList = productService.getAllProducts();
        List<GetAllProductResponseDto> getAllProductResponseDto = productDtoMapper.toGetAllProductResponseDtoList(productResponseDataList);
        return getAllProductResponseDto;

    }

    @GetMapping("/{pid}")
    public ProductResponseDto getProductByPid(@PathVariable Integer pid){
        ProductResponseData productResponseData = productService.getProductByPid(pid);
        ProductResponseDto productResponseDto = productDtoMapper.toProductResponseDto(productResponseData);
        return productResponseDto;


    }

}

