package com.fsse2603.project_backend.mapper.cartItem;

import com.fsse2603.project_backend.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.mapper.product.ProductDataMapper;
import com.fsse2603.project_backend.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //become Springbeans
public class CartItemDataMapper{
    private final ProductDataMapper productDataMapper;
    private final UserDataMapper userDataMapper;

    public CartItemDataMapper(ProductDataMapper productDataMapper, UserDataMapper userDataMapper) {
        this.productDataMapper = productDataMapper;
        this.userDataMapper = userDataMapper;
    }

    public CartItemResponseData toCartItemResponseData(CartItemEntity cartItemEntity){
        CartItemResponseData response = new CartItemResponseData();
        response.setCid(cartItemEntity.getCid());
        response.setProduct(productDataMapper.toProductResponseData(cartItemEntity.getProduct()));

        response.setUser(userDataMapper.toUserResponseData(cartItemEntity. getUser()));
        response.setQuantity(cartItemEntity.getQuantity());
        return response;
    }

    public List<CartItemResponseData> toCartItemResponseDataList(List<CartItemEntity> entityList){
        List<CartItemResponseData> list = new ArrayList<>();
        for (CartItemEntity entity : entityList){
            list.add(toCartItemResponseData(entity));
        }
        return list;

    }
}
