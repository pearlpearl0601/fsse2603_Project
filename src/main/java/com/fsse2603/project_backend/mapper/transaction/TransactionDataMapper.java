package com.fsse2603.project_backend.mapper.transaction;

import com.fsse2603.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2603.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2603.project_backend.mapper.transactionProduct.TransactionProductDataMapper;
import com.fsse2603.project_backend.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionDataMapper {

    private final UserDataMapper userDataMapper;
    private final TransactionProductDataMapper transactionProductDataMapper;

    public TransactionDataMapper(
            UserDataMapper userDataMapper,
            TransactionProductDataMapper transactionProductDataMapper) {

        this.userDataMapper = userDataMapper;
        this.transactionProductDataMapper = transactionProductDataMapper;
    }

    public TransactionResponseData toTransactionResponseData(
            TransactionEntity transactionEntity) {

        TransactionResponseData responseData =
                new TransactionResponseData();

        responseData.setTid(transactionEntity.getTid());

        responseData.setBuyer(
                userDataMapper.toUserResponseData(
                        transactionEntity.getBuyer()
                )
        );

        responseData.setDateTime(
                transactionEntity.getDateTime()
        );

        responseData.setStatus(
                transactionEntity.getStatus()
        );

        responseData.setTotal(
                transactionEntity.getTotal()
        );

        responseData.setTransactionProductList(
                transactionProductDataMapper
                        .toTransactionProductResponseDataList(
                                transactionEntity
                                        .getTransactionProductList()
                        )
        );

        return responseData;
    }
}