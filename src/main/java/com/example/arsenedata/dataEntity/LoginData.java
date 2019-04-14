package com.example.arsenedata.dataEntity;

import javax.validation.constraints.NotNull;

public class LoginData
{
    @NotNull
    private String username;

    @NotNull
    private String password;

    private String firstName;

    private String lastName;

    public LoginData()
    {
    }

    //This is like a schema to be used to get or fetch data
    public LoginData(@NotNull String username, @NotNull String password, String firstName, String lastName)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    @Override
    public String toString()
    {
        return "LoginData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
