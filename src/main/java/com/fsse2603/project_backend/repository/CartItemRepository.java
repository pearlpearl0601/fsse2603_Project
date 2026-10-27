package com.fsse2603.project_backend.repository;

import com.fsse2603.project_backend.data.cartItem.entity.CartItemEntity;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItemEntity, Integer> {
    Optional<CartItemEntity> findByProduct_PidAndUser_Uid(Integer pid, Integer uid);

    List<CartItemEntity> findAllByUser(UserEntity user);

    int deleteByUserAndProduct_Pid(UserEntity user, Integer pid );

    void deleteAllByUser(UserEntity user);
}

