package com.fsse2603.project_backend.mapper.user;

import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.data.user.domainObject.response.UserResponseData;
import com.fsse2603.project_backend.data.user.entity.UserEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class UserDataMapper{
    public FirebaseUserData toFirebaseUserData (Jwt jwt){
        FirebaseUserData firebaseUserdata = new FirebaseUserData();
        firebaseUserdata.setFirebaseUid(jwt.getClaimAsString("user_id"));
        firebaseUserdata.setEmail(jwt.getClaimAsString("email"));
        return firebaseUserdata;
    }
    public UserResponseData toUserResponseData(UserEntity userEntity){
        UserResponseData userResponseData = new UserResponseData();
        userResponseData.setUid(userEntity.getUid());
        userResponseData.setEmail(userEntity.getEmail());
        userResponseData.setFirebaseUid(userEntity.getFirebaseUid());
        return userResponseData;
    }
}


