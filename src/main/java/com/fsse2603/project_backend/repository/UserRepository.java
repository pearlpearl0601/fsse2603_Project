package com.fsse2603.project_backend.repository;

import com.fsse2603.project_backend.data.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {  //primary key is integer
    //derived query
    Optional<UserEntity> findByEmail(String email);
}

