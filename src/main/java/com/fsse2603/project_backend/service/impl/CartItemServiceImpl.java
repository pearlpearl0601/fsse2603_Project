package com.fsse2603.project_backend.service.impl;

import com.fsse2603.project_backend.data.cartItem.domainObject.response.CartItemResponseData;
import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.product.entity.ProductEntity;
import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import com.fsse2603.project_backend.exception.CartItemException;
import com.fsse2603.project_backend.mapper.cartItem.CartItemDataMapper;
import com.fsse2603.project_backend.mapper.cartItem.CartItemEntityMapper;
import com.fsse2603.project_backend.repository.CartItemRepository;
import com.fsse2603.project_backend.service.CartItemService;
import com.fsse2603.project_backend.service.ProductService;
import com.fsse2603.project_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartItemEntityMapper cartItemEntityMapper;
    private final CartItemDataMapper cartItemDataMapper;

    public CartItemServiceImpl(UserService userService, CartItemRepository cartItemRepository, ProductService productService, CartItemEntityMapper cartItemEntityMapper, CartItemDataMapper cartItemDataMapper) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.cartItemEntityMapper = cartItemEntityMapper;
        this.cartItemDataMapper = cartItemDataMapper;
    }

    @Transactional
    @Override
    public void putCartItem(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        if(quantity <=0){
            throw new CartItemException();
        }
        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid, userEntity.getUid());  //pid is listed above, so no need .get

        if(optionalCartItemEntity.isPresent()){
            CartItemEntity cartItemEntity = optionalCartItemEntity.get();
            //Quantity in cartItem already  + the quantity user want to add
            if(cartItemEntity.getQuantity() + quantity > cartItemEntity.getProduct().getStock()){
                throw new CartItemException();
            }
            cartItemEntity.setQuantity(cartItemEntity.getQuantity() + quantity);
        }else{ //no cart item, you need to find the pid from product and create it
            ProductEntity productEntity = productService.getEntityByPid(pid);

            if(quantity > productEntity.getStock()){
                throw new CartItemException();
            }
            CartItemEntity cartItemEntity = cartItemEntityMapper.toCartItemEntity(userEntity, productEntity, quantity);
            cartItemRepository.save(cartItemEntity);	//no response, so keep void is ok
        }

    }

    @Override
    public List<CartItemResponseData> getUserCart(FirebaseUserData firebaseUserData){
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(userEntity);
        return cartItemDataMapper.toCartItemResponseDataList(cartItemEntityList);
    }
    @Transactional
    @Override
    public void  updateCartQuantity(FirebaseUserData firebaseUserData, Integer pid, Integer quantity){
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        if(quantity <= 0 ){
            throw new CartItemException();
        }

        CartItemEntity cartItemEntity = getEntityByUidAndPid(userEntity.getUid(), pid);

        if(quantity > cartItemEntity.getProduct().getStock()){
            throw new CartItemException();
        }
        cartItemEntity.setQuantity(quantity);
    }
    @Transactional
    @Override
    public void deleteCartItem(FirebaseUserData firebaseUserData, Integer pid){
        UserEntity userEntity = userService.getUserEntityByFirebaseUserData(firebaseUserData);

        int count = cartItemRepository.deleteByUserAndProduct_Pid(userEntity, pid);
        if(count <= 0 ){
            throw new CartItemException();
        }
    }
    @Override
    public List<CartItemEntity > getEntityListByUserEntity(UserEntity userEntity){
        return cartItemRepository.findAllByUser(userEntity);
    }


    public CartItemEntity getEntityByUidAndPid(Integer uid, Integer pid){
        Optional<CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid, uid);
        if(optionalCartItemEntity.isEmpty()){
            throw new CartItemException();
        }
        return optionalCartItemEntity.get();
    }

    @Override
    public void emptyUserCart(UserEntity userEntity){
        cartItemRepository.deleteAllByUser(userEntity);
    }



}



