package com.fsse2603.project_backend.service;

import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2603.project_backend.data.transactionProduct.entity.TransactionProductEntity;

public interface TransactionProductService {
    TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity);
}
