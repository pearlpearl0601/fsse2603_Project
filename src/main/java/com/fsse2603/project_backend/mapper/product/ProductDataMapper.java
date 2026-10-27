package com.fsse2603.project_backend.mapper.product;

import com.fsse2603.project_backend.data.product.domainObject.response.ProductResponseData;
import com.fsse2603.project_backend.data.product.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDataMapper{
    public ProductResponseData toProductResponseData(ProductEntity productEntity){
        ProductResponseData productResponseData = new ProductResponseData();
        productResponseData.setPid(productEntity.getPid());
        productResponseData.setName(productEntity.getName());
        productResponseData.setDescription(productEntity.getDescription());
        productResponseData.setImageUrl(productEntity.getImageUrl());
        productResponseData.setPrice(productEntity.getPrice());
        productResponseData.setStock(productEntity.getStock());

        return productResponseData;
    }

    public List<ProductResponseData> toProducResponseDataList(Iterable<ProductEntity> productEntityIterable){
        List<ProductResponseData> productResponseDataList = new ArrayList<>();
        for(ProductEntity productEntity: productEntityIterable){
            productResponseDataList.add(toProductResponseData(productEntity));

        }

        return productResponseDataList;
    }
}
