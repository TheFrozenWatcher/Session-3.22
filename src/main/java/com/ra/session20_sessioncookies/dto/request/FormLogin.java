package com.ra.session20_sessioncookies.dto.request;

public class FormLogin
{
    private String username;
    private String password;

    public FormLogin()
    {
    }

    public FormLogin(String password, String username)
    {
        this.password = password;
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

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
