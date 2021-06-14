package com.jromero.redsocial.service;

import java.util.List;

import com.jromero.redsocial.dto.LoginDTO;
import com.jromero.redsocial.dto.TokenResponseDTO;
import com.jromero.redsocial.iservice.IJwtService;
import com.jromero.redsocial.iservice.IUserService;
import com.jromero.redsocial.models.UserModel;
import com.jromero.redsocial.repository.UserAuthRepository;
import com.jromero.redsocial.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository authRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IJwtService jwtService;

    @Override
    public List<UserModel> getUsers() {
        return (List<UserModel>) userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(UserModel user) {
        userRepository.saveUser(user.getNames(), 
            user.getNick(),
            user.getEmail(), 
            user.getSex(), 
            user.getBornDate());

        UserModel u = userRepository.findByEmail(user.getEmail());
        
        String password = passwordEncoder.encode(user.getAuth().getPassword());
        authRepository.addUserAuth(u.getId(), 
            password); 
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByNick(username);
        return user;
    }

    @Override
    public TokenResponseDTO login(LoginDTO loginData) {
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
            loginData.getNick(),
            loginData.getPassword()
        );
        authenticationManager.authenticate(auth);
        String token = jwtService.createToken(loadUserByUsername(loginData.getNick()));
        TokenResponseDTO response = new TokenResponseDTO();
        response.setToken(token);
        return response;
    }


    
}
