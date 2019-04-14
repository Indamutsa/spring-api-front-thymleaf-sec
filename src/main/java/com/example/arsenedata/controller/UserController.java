package com.example.arsenedata.controller;


import com.example.arsenedata.dataEntity.LoginData;
import com.example.arsenedata.dataEntity.User;
import com.example.arsenedata.repository.UserRepository;
import com.example.arsenedata.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;


    @PostMapping(value = "/signin")
    public String login(@RequestBody @Valid LoginData loginData, HttpServletRequest request, HttpServletResponse response)
    {
        System.out.println(loginData.toString());
        return userService.signin(loginData.getUsername(), loginData.getPassword())
                .orElseThrow(()->  new HttpServerErrorException(HttpStatus.FORBIDDEN, "Login Failed"));
    }

    @PostMapping("/signup")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //This is a middle to force the user to identify
    @ResponseStatus(HttpStatus.CREATED)
    public User signup(@RequestBody @Valid  LoginData loginData)
    {
        return userService.signup(loginData.getUsername(), loginData.getPassword(),
                loginData.getFirstName(), loginData.getLastName())
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST,"User already exists"));
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }


    //Exception handler if NoSuchElementException is thrown in this Controller
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String return400(RuntimeException ex) {
        log.error("Unable to complete transaction", ex);
        return ex.getMessage();
    }
}
