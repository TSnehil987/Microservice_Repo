package com.ts.photoapp.users.repository;

import org.springframework.data.repository.CrudRepository;
import com.ts.photoapp.users.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

	UserEntity findByEmail(String email); 
}
