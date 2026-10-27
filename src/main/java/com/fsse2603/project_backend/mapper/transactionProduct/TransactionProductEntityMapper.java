package com.fsse2603.project_backend.mapper.transactionProduct;

import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2603.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionProductEntityMapper{
    public TransactionProductEntity toTransactionProductEntity(TransactionEntity transactionEntity, CartItemEntity cartItemEntity){
        TransactionProductEntity transactionProductEntity = new TransactionProductEntity();
        transactionProductEntity.setTransaction(transactionEntity);
        transactionProductEntity.setPid(cartItemEntity.getProduct().getPid());
        transactionProductEntity.setName(cartItemEntity.getProduct().getName());
        transactionProductEntity.setDescription(cartItemEntity.getProduct().getDescription());
        transactionProductEntity.setImageUrl(cartItemEntity.getProduct().getImageUrl ());
        transactionProductEntity.setPrice(cartItemEntity.getProduct().getPrice ());
        transactionProductEntity.setStock(cartItemEntity.getProduct().getStock ());
        transactionProductEntity.setQuantity(cartItemEntity.getQuantity());
        return transactionProductEntity;


    }
}

