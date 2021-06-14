package com.jromero.redsocial.repository;

import java.sql.Date;

import com.jromero.redsocial.models.UserModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {

    @Modifying
    @Query(
        value = "INSERT INTO USUTBL\n"
        + "(USUNOM,\n"
        + "USUNICK,\n"
        + "USUEMAIL,\n"
        + "USUGEN,\n"
        + "USUFECHNAC)\n"
        + "VALUES(\n"
        + ":name,\n"
        + ":nick,\n"
        + ":email,\n"
        + ":sex,\n"
        + ":fech_nac)\n",
        nativeQuery = true
    )
    public Integer saveUser(
        @Param("name") String name,
        @Param("nick") String nick,
        @Param("email") String email,
        @Param("sex") String sex,
        @Param("fech_nac") Date bornDate
    );

    public UserModel findByNick(String nick);
    public UserModel findByEmail(String email);
}
