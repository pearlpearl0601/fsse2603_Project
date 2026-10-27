package com.fsse2603.project_backend.service;

import com.fsse2603.project_backend.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CartItemService {
    @Transactional // dirty check
    public void putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);

    List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData);

    @Transactional
    public void updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity);


    @Transactional
    void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid);

    List<CartItemEntity> getEntityListByUserEntity(UserEntity userEntity);

    void emptyUserCart(UserEntity userEntity);
}
