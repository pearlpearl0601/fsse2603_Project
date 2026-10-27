package com.fsse2603.project_backend.service.impl;

import com.fsse2603.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2603.project_backend.data.product.entity.ProductEntity;
import com.fsse2603.project_backend.exception.ProductException;
import com.fsse2603.project_backend.mapper.product.ProductDataMapper;
import com.fsse2603.project_backend.repository.ProductRepository;
import com.fsse2603.project_backend.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductDataMapper productDataMapper) {
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }

    @Override
    public ProductResponseData createProduct(ProductEntity productEntity) {
        // Save entity to DB
        ProductEntity savedEntity = productRepository.save(productEntity);

        // Convert to response DTO
        return productDataMapper.toProductResponseData(savedEntity);
    }

    @Override
    public List<ProductResponseData> getAllProducts(){
        Iterable<ProductEntity> productEntityIterable = productRepository.findAll();
        List<ProductResponseData> productResponseDataList = productDataMapper.toProducResponseDataList(productEntityIterable);
        return productResponseDataList;
    }

    @Override
    public ProductResponseData getProductByPid(Integer pid){
        ProductEntity productEntity = getEntityByPid(pid);
        ProductResponseData productResponseData = productDataMapper.toProductResponseData(productEntity);
        return productResponseData;

    }
    @Override
    public ProductEntity getEntityByPid(Integer pid){
        Optional<ProductEntity> optionalProductEntity = productRepository.findById(pid);

        if(optionalProductEntity.isEmpty()){
            throw new ProductException();
        }

        return optionalProductEntity.get();
    }

    @Transactional
    @Override
    public void reduceStock(Integer pid, Integer quantity){
        ProductEntity productEntity = getEntityByPid(pid);

        if(productEntity.getStock() < quantity){
            throw new ProductException();
        }
        productEntity.setStock(productEntity.getStock() - quantity);
    }


}


