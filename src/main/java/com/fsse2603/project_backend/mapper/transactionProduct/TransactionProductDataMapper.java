package com.fsse2603.project_backend.mapper.transactionProduct;

import com.fsse2603.project_backend.data.transactionProduct.domainObject.TransactionProductResponseData;
import com.fsse2603.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class TransactionProductDataMapper {
    public TransactionProductResponseData toTransactionProductResponseData(TransactionProductEntity transactionProductEntity) {
        TransactionProductResponseData responseData = new TransactionProductResponseData();
        responseData.setTpid(transactionProductEntity.getTpid());
        responseData.setPid(transactionProductEntity.getPid());
        responseData.setName(transactionProductEntity.getName());
        responseData.setDescription(transactionProductEntity.getDescription());
        responseData.setImageUrl(transactionProductEntity.getImageUrl());
        responseData.setPrice(transactionProductEntity.getPrice());
        responseData.setStock(transactionProductEntity.getStock());
        responseData.setQuantity(transactionProductEntity.getQuantity());
        return responseData;

    }

    public List<TransactionProductResponseData>
    toTransactionProductResponseDataList(
            List<TransactionProductEntity> entityList) {

        List<TransactionProductResponseData> result =
                new ArrayList<>();

        for (TransactionProductEntity entity : entityList) {

            result.add(
                    toTransactionProductResponseData(entity)
            );
        }

        return result;
    }

}


