package com.fsse2603.project_backend.service.impl;

import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2603.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2603.project_backend.mapper.transactionProduct.TransactionProductEntityMapper;
import com.fsse2603.project_backend.repository.TransactionProductRepository;
import com.fsse2603.project_backend.service.TransactionProductService;
import org.springframework.stereotype.Service;

@Service
public class TransactionProductServiceImpl implements TransactionProductService {

    private final TransactionProductEntityMapper transactionProductEntityMapper;
    private final TransactionProductRepository transactionProductRepository;

    public TransactionProductServiceImpl(TransactionProductEntityMapper transactionProductEntityMapper, TransactionProductRepository transactionProductRepository) {
        this.transactionProductEntityMapper = transactionProductEntityMapper;
        this.transactionProductRepository = transactionProductRepository;
    }

    @Override
    public TransactionProductEntity createTransactionProduct(TransactionEntity transactionEntity, CartItemEntity cartItemEntity){
        TransactionProductEntity transactionProductEntity = transactionProductEntityMapper.toTransactionProductEntity(transactionEntity, cartItemEntity);
        return transactionProductRepository.save(transactionProductEntity);

    }
}

