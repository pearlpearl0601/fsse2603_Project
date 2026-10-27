package com.fsse2603.project_backend.service.impl;

import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.transaction.domainObject.TransactionResponseData;
import com.fsse2603.project_backend.data.transaction.entity.TransactionEntity;
import com.fsse2603.project_backend.data.transaction.status.TransactionStatus;
import com.fsse2603.project_backend.data.transactionProduct.entity.TransactionProductEntity;
import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import com.fsse2603.project_backend.exception.TransactionException;
import com.fsse2603.project_backend.mapper.transaction.TransactionDataMapper;
import com.fsse2603.project_backend.mapper.transaction.TransactionEntityMapper;
import com.fsse2603.project_backend.repository.TransactionRepository;
import com.fsse2603.project_backend.service.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionServiceImpl implements TransactionService {

    private final UserService userService;
    private final CartItemService cartItemService;
    private final TransactionEntityMapper transactionEntityMapper;
    private final TransactionRepository transactionRepository;
    private final TransactionProductService transactionProductService;
    private final TransactionDataMapper transactionDataMapper;
    private final ProductService productService;

    public TransactionServiceImpl(UserService userService, CartItemService cartItemService, TransactionEntityMapper transactionEntityMapper, TransactionRepository transactionRepository, TransactionProductService transactionProductService, TransactionDataMapper transactionDataMapper, ProductService productService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.transactionEntityMapper = transactionEntityMapper;
        this.transactionRepository = transactionRepository;
        this.transactionProductService = transactionProductService;
        this.transactionDataMapper = transactionDataMapper;
        this.productService = productService;
    }

    @Transactional
    @Override
    public TransactionResponseData prepareTransaction(FirebaseUserData firebaseUserData){

        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        List<CartItemEntity> cartItemEntityList = cartItemService.getEntityListByUserEntity(userEntity);

        if (cartItemEntityList.isEmpty()){
            throw new TransactionException();
        }
//from userEntity to transactionEntity (userEntityList -> userEntity -> transactionEntity)
        TransactionEntity transactionEntity = transactionEntityMapper.toTransactionEntity(userEntity);
        transactionEntity = transactionRepository.save(transactionEntity);

        for(CartItemEntity cartItemEntity : cartItemEntityList){
            TransactionProductEntity transactionProductEntity = transactionProductService.createTransactionProduct(transactionEntity, cartItemEntity);
            transactionEntity.getTransactionProductList().add(transactionProductEntity);
            transactionEntity.setTotal(
                    transactionEntity.getTotal().add(
                            transactionProductEntity.getPrice().multiply(new BigDecimal(transactionProductEntity.getQuantity()))
                    )
            );
        }
        return transactionDataMapper.toTransactionResponseData(transactionEntity);
    }

    @Override
    public TransactionResponseData getTransactionByTid(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);


        TransactionEntity transactionEntity =
                getEntityByTid(tid);

        validateTransactionUser(transactionEntity, userEntity);

        validateTransactionUser(
                transactionEntity,
                userEntity
        );
        return transactionDataMapper.toTransactionResponseData(transactionEntity);
    }
    @Transactional
    @Override
    public void processTransaction(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);
        TransactionEntity transactionEntity = getEntityByTid(tid);
        //ensure only the users can view their own transaction record
        validateTransactionUser(transactionEntity, userEntity);
        //status should be one after another
        if(transactionEntity.getStatus() != TransactionStatus.PREPARE){
            throw new TransactionException();
        }
        transactionEntity.setStatus(TransactionStatus.PROCESSING);

    }

    @Transactional
    @Override
    public TransactionResponseData successTransactionByTid(FirebaseUserData firebaseUserData, Integer tid){
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);
        TransactionEntity transactionEntity = getEntityByTid(tid);
        //ensure only the users can view their own transaction record
        validateTransactionUser(transactionEntity, userEntity);
        //status should be one after another
        if(transactionEntity.getStatus() != TransactionStatus.PROCESSING){
            throw new TransactionException();
        }

        for(TransactionProductEntity transactionProductEntity: transactionEntity.getTransactionProductList()){
            productService.reduceStock(transactionProductEntity.getPid(), transactionProductEntity.getQuantity());
        }
        cartItemService.emptyUserCart(userEntity);
        transactionEntity.setStatus(TransactionStatus.SUCCESS);

        return transactionDataMapper.toTransactionResponseData(transactionEntity);
    }

    public TransactionEntity getEntityByTid(Integer tid){
        Optional<TransactionEntity> optionalTransactionEntity = transactionRepository.findById(tid);
        if(optionalTransactionEntity.isEmpty()){
            throw new TransactionException();
        }
        return optionalTransactionEntity.get();
    }
    public void validateTransactionUser(TransactionEntity transactionEntity, UserEntity userEntity){
        if (!transactionEntity.getBuyer().getUid().equals(userEntity.getUid())){
            throw new TransactionException();
        }
    }

}



