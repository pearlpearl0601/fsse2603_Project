package com.fsse2603.project_backend.mapper.transaction;

import com.fsse2603.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2603.project_backend.data.transaction.status.TransactionStatus;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class TransactionEntityMapper{
    public TransactionEntity toTransactionEntity(UserEntity userEntity){
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setBuyer(userEntity);
        transactionEntity.setDateTime(LocalDateTime.now());
        transactionEntity.setStatus(TransactionStatus.PREPARE);
        transactionEntity.setTotal(BigDecimal.ZERO);
        return transactionEntity;
    }
}
