package com.fsse2603.project_backend.mapper.cartItem;

import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.product.entity.ProductEntity;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component //become Springbeans
public class CartItemEntityMapper{
    public CartItemEntity toCartItemEntity(UserEntity userEntity, ProductEntity productEntity, Integer quantity){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setUser(userEntity);
        cartItemEntity.setProduct(productEntity);
        cartItemEntity.setQuantity(quantity);
        return cartItemEntity;

    }
}
