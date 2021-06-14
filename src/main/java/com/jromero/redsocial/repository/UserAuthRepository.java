package com.jromero.redsocial.repository;

import com.jromero.redsocial.models.UserAuth;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends CrudRepository<UserAuth, Integer>{
    
    @Modifying
    @Query(
        value = "INSERT INTO USUAUTH\n"
        + "(USUID,\n"
        + "USUPASS)\n"
        + "values("
        + ":id,\n"
        + ":password)\n",
        nativeQuery = true
    )
    public void addUserAuth(
        @Param("id") Integer id,
        @Param("password") String password
    );
}
