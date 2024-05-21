package com.ra.session20_sessioncookies.dto.request;

import javax.validation.constraints.NotNull;

public class FormRegister
{
    @NotNull(message = "Full name must not null")
    private String fullName;
    @NotNull(message = "Username must not null")
    private String username;
    @NotNull(message = "Password must not null")
    private String password;

    public FormRegister()
    {
    }

    public FormRegister(String fullName, String password, String username)
    {
        this.fullName = fullName;
        this.password = password;
        this.username = username;
    }

    public String getFullName()
    {
        return fullName;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
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
