package com.fsse2603.project_backend.service.impl;

import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import com.fsse2603.project_backend.mapper.user.UserEntityMapper;
import com.fsse2603.project_backend.repository.UserRepository;
import com.fsse2603.project_backend.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserServiceImpl(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public UserEntity getUserEntityByFirebaseUserData(FirebaseUserData fireBaseUserData) {
        //firebaseUserData <- from data,domainObject
        Optional<UserEntity> userEntity = userRepository.findByEmail(fireBaseUserData.getEmail());

        if (userEntity.isEmpty()) {
            UserEntity newUserEntity = userEntityMapper.toUserEntity(fireBaseUserData);
            return userRepository.save(newUserEntity);
        } else {
            return userEntity.get();
        }

    }

}


