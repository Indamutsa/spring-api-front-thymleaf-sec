package com.example.arsenedata.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

public class JwtTokenFilter extends GenericFilterBean
{
    private static final Logger log = LoggerFactory.getLogger(JwtTokenFilter.class);
    private static final String BEARER = "Bearer";

    private DataUserDetailsService dataUserDetailsService;

    public JwtTokenFilter(DataUserDetailsService dataUserDetailsService)
    {
        this.dataUserDetailsService = dataUserDetailsService;
    }

    //Get the the http request header and check if JWT as part of it
    //  If it is valid, set the context with the Authentication( user and roles) found in the token
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException
    {
        log.info("Process request to check for a JSON web token");

        //Check for authorization : Bearer Jwt
        String headerValue = ((HttpServletRequest) servletRequest).getHeader("Authorization");
        getBearerToken(headerValue).ifPresent(token ->
        {
            //Extract the username and roles from the JWT and construct user details
            dataUserDetailsService.loadUserByJwtToken(token).ifPresent(userDetails ->
            {
                //Add user details (Permissions) to the  context for jsut API invocation
                SecurityContextHolder.getContext().setAuthentication(
                        new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
            });
        });

        //Move on to the next filter in the chains
        filterChain.doFilter(servletRequest, servletResponse);

    }

    //Extract the token from the header
    private Optional<String> getBearerToken(String headerValue)
    {
        if (headerValue != null && headerValue.startsWith(BEARER))
        {
            return Optional.of(headerValue.replace(BEARER, "").trim());
        }

        return Optional.empty();
    }
}
