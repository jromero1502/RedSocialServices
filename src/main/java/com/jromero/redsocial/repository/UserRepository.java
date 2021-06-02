package com.jromero.redsocial.repository;

import com.jromero.redsocial.models.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel, Long> {
}
