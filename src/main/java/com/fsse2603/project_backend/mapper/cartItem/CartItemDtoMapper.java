package com.fsse2603.project_backend.mapper.cartItem;

import com.fsse2603.project_backend.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2603.project_backend.data.cartItem.dto.response.CartItemResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //become Springbeans
public class CartItemDtoMapper{
    public CartItemResponseDto toCartItemResponseDto (CartItemResponseData data){
        CartItemResponseDto dto = new CartItemResponseDto();

        dto.setPid(data.getProduct().getPid()); // product ID
        dto.setName(data.getProduct().getName()); // product name
        dto.setImageUrl(data.getProduct().getImageUrl()); // product image
        dto.setStock(data.getProduct().getStock()); // product stock
        dto.setPrice(data.getProduct().getPrice());
        dto.setCartQuantity(data.getQuantity());// cart quantity (comes from cart item

        return dto;

    }

    public List<CartItemResponseDto> toCartItemResponseDtoList (List<CartItemResponseData> dataList){
        List<CartItemResponseDto> dtoList = new ArrayList<>();
        for (CartItemResponseData data : dataList){
            dtoList.add(toCartItemResponseDto(data));
        }
        return dtoList;

    }
}

