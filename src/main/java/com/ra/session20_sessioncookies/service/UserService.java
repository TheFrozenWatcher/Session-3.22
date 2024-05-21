package com.ra.session20_sessioncookies.service;

import com.ra.session20_sessioncookies.dao.UserDao;
import com.ra.session20_sessioncookies.dto.request.FormLogin;
import com.ra.session20_sessioncookies.dto.request.FormRegister;
import com.ra.session20_sessioncookies.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public boolean registerUser(FormRegister formRegister)
    {
        User user = new User();
        user.setFullName(formRegister.getFullName());
        user.setUsername(formRegister.getUsername());
        user.setPassword(formRegister.getPassword());
        List<User> userList = userDao.getAllUser();
        if (userList.stream().anyMatch(u -> u.getUsername().equals(formRegister.getUsername())))
        {
            return false;
        }
        return userDao.registerUser(user);
    }

    public User loginUser(FormLogin formLogin)
    {
        return userDao.loginUser(formLogin);
    }
}
