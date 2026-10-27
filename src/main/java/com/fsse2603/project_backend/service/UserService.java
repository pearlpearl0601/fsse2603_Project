package com.fsse2603.project_backend.service;

import com.fsse2603.project_backend.data.user.domainObject.request.FirebaseUserData;
import com.fsse2603.project_backend.data.user.entity.UserEntity;

public interface UserService {
    UserEntity getUserEntityByFirebaseUserData(FirebaseUserData fireBaseUserData);

}
