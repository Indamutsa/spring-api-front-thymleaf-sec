package com.example.arsenedata.security;

import com.example.arsenedata.dataEntity.Role;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider
{
    private String role_key = "roles";
    private JwtParser jwtParser;
    private String secretKey;
    private long validityInMilliseconds;

    @Autowired
    public JwtTokenProvider(@Value("${security.jwt.token.secret-key}") String secretKey,
                            @Value("${security.jwt.token.expiration}") long validityInMilliseconds)
    {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        this.validityInMilliseconds = validityInMilliseconds;
    }

    //Given the username, create a token
    public String createToken(String username, List<Role> roles)
    {
        //Add the username to the payload
        Claims claims = Jwts.claims().setSubject(username);

        //Convert the roles to spring security SimpleGrantedAuthority objects
        //Add to simple Granted Authority objects to claims
        claims.put(role_key, roles.stream().map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        //Build the token
        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityInMilliseconds))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    //Make sure the token is valid
    public boolean isTokenValid(String token)
    {
        try
        {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException ds)
        {
            return false;
        }
    }

    //Getting the roles from the token
    public List<GrantedAuthority> getRoles(String token)
    {
        //Map the user to the role or authority
        List<Map<String, String>> roleClaims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().get(role_key, List.class);

        //Retrieve user authority
        return roleClaims.stream().map(roleClaim -> new SimpleGrantedAuthority(roleClaim.get("authority")))
                .collect(Collectors.toList());
    }

    //Get the username from the token string
    public String getUserName(String token)
    {
        //Retrieve the username from the token
        String username = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();

        return username;
    }
}
