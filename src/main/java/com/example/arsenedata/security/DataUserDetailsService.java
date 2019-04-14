package com.example.arsenedata.security;


import com.example.arsenedata.dataEntity.User;
import com.example.arsenedata.repository.RoleRepository;
import com.example.arsenedata.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.security.core.userdetails.User.withUsername;

@Component
public class DataUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with name %s does not exist", username)));
        System.out.println(user.toString());

        return withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

    //Extract the username and roles from a valid jwt string
    public Optional<UserDetails> loadUserByJwtToken(String jwtToken)
    {
        if (jwtTokenProvider.isTokenValid(jwtToken))
        {
            return Optional.of(
                    withUsername(jwtTokenProvider.getUserName(jwtToken))
                            .authorities(jwtTokenProvider.getRoles(jwtToken))
                            .password("") //Token does not have the password but the field might have something
                            .accountExpired(false)
                            .accountLocked(false)
                            .credentialsExpired(false)
                            .disabled(false)
                            .build());
        }
        return Optional.empty();
    }

    //Extract the username from JWT and lookup in the database
    public Optional <UserDetails> loadUserByJwtTokenAndDatabse(String jwtToken){
        if(jwtTokenProvider.isTokenValid(jwtToken))
        {
            return Optional.of(loadUserByUsername(jwtTokenProvider.getUserName(jwtToken)));
        }
        else
        {
            return Optional.empty();
        }
    }
}

