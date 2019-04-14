package com.example.arsenedata.service;

import com.example.arsenedata.dataEntity.Role;
import com.example.arsenedata.dataEntity.User;
import com.example.arsenedata.repository.RoleRepository;
import com.example.arsenedata.repository.UserRepository;
import com.example.arsenedata.security.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private UserRepository userRepository;
    private AuthenticationManager authenticationManager;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider jwtTokenProvider;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);


    @Autowired
    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager,
                       RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider)
    {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    //Signin in the applicatioon with JWT-enabled authentication
    public Optional<String> signin(String username, String password)
    {
//        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        LOGGER.info("New user attempting to sign in");

        Optional<String> token = Optional.empty();
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent())
        {
            try
            {
                //This will authenticate the user by searching him in the database and then compare
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

                //When he is authenticated, we will create a token
                token = Optional.of(jwtTokenProvider.createToken(username, user.get().getRoles()));
            } catch (AuthenticationException e)
            {
                LOGGER.info("Log in failed for user {}", username);
            }
        }

        return token;
    }

    /**
    * @param username username
    * @param password password
    * @param firstName first name
    * @param lastName last name
    *
    * */
    public Optional<User> signup(String username, String password, String firstName, String lastName)
    {
        LOGGER.info("New user attempting to sign in");
        Optional<User> user = Optional.empty();

        //If there is no such user, he can sign up
        if (!userRepository.findByUsername(username).isPresent())
        {
            //By defaulf we assign him the user if ROLE_CSR but we need to dynamically do this
            Optional<Role> role = roleRepository.findByRoleName("ROLE_CSR");

            //Then we save the user with his password hashed, with his role, and names
            user = Optional.of(userRepository.save
                    (new User(username,
                            passwordEncoder.encode(password),
                            role.get(),
                            firstName,
                            lastName)));
        }
        return user;
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
