package com.fsse2603.project_backend.mapper.user;

import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserEntityMapper{
    public UserEntity toUserEntity (FirebaseUserData firebaseUserData){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(firebaseUserData.getEmail());
        userEntity.setFirebaseUid(firebaseUserData.getFirebaseUid());
        return userEntity;
    }
}


