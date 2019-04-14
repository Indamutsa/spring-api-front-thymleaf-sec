package com.example.arsenedata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArsenedataApplication
{

    /*
    * In Spring when you log in, data used can be retrieved from UserDetails
    * When you logout, the configure(http) will clear out the authentication, the programmer does not intervene
    *
    * It's not a good practice to save the token
    *
    * To understand how everything is chained, please print the message, as it runs you will see how
    * otherwise spring is not self - explicit but it makes things easy
    * */

    public static void main(String[] args)
    {
        SpringApplication.run(ArsenedataApplication.class, args);
    }
}

