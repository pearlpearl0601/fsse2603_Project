package com.fsse2603.project_backend.controller;

import com.fsse2603.project_backend.data.cartItem.dto.response.CartItemResponseDto;
import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.mapper.cartItem.CartItemDtoMapper;
import com.fsse2603.project_backend.mapper.user.UserDataMapper;
import com.fsse2603.project_backend.service.CartItemService;
import org.springframework.boot.security.oauth2.server.resource.autoconfigure.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/items")

public class CartItemController{
    private final UserDataMapper userDataMapper;
    private final CartItemService cartItemService;
    private final CartItemDtoMapper cartItemDtoMapper;

    public CartItemController(UserDataMapper userDataMapper, CartItemService cartItemService, CartItemDtoMapper cartItemDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.cartItemService = cartItemService;
        this.cartItemDtoMapper = cartItemDtoMapper;
    }

    @PutMapping("/{pid}/{quantity}") //2 path variables
    @ResponseStatus(HttpStatus.NO_CONTENT) //Request succeeded, and there is intentionally no response body
    public void putCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.putCartItem(firebaseUserData, pid, quantity);
    }
    @GetMapping
    public List<CartItemResponseDto> getUserCart(@AuthenticationPrincipal Jwt jwt){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        return cartItemDtoMapper.toCartItemResponseDtoList(
                cartItemService.getUserCart(firebaseUserData)
        );

        }

    @PatchMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartQuantity(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.updateCartQuantity(firebaseUserData, pid, quantity);
    }
    @DeleteMapping("/{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid){
        FirebaseUserData firebaseUserData = userDataMapper.toFirebaseUserData(jwt);
        cartItemService.deleteCartItem(firebaseUserData,pid);
    }
}
