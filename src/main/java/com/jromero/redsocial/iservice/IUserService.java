package com.jromero.redsocial.iservice;

import java.util.List;

import com.jromero.redsocial.dto.LoginDTO;
import com.jromero.redsocial.dto.TokenResponseDTO;
import com.jromero.redsocial.models.UserModel;

public interface IUserService {
    public List<UserModel> getUsers();
    public void addUser(UserModel user);
    public TokenResponseDTO login(LoginDTO loginData);
}
