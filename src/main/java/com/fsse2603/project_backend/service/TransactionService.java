package com.fsse2603.project_backend.service;

import com.fsse2603.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import jakarta.transaction.Transactional;

public interface TransactionService {
    @Transactional
    TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData);

    TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);

    @Transactional
    void processTransaction(FirebaseUserData firebaseUserData, Integer tid);

    @Transactional
    TransactionResponseData successTransactionByTid(FirebaseUserData firebaseUserData, Integer tid);
}
