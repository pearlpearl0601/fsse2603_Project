package com.fsse2603.project_backend.service;

import com.fsse2603.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2603.project_backend.data.product.entity.ProductEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface ProductService {
    ProductResponseData createProduct(ProductEntity productEntity);

    List<ProductResponseData> getAllProducts();

    ProductResponseData getProductByPid(Integer pid);

    ProductEntity getEntityByPid(Integer pid);

    @Transactional
    void reduceStock(Integer pid, Integer quantity);
}
