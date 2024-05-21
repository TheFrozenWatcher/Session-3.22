package com.ra.session20_sessioncookies.dao;

import com.ra.session20_sessioncookies.dto.request.FormLogin;
import com.ra.session20_sessioncookies.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDao
{
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public boolean registerUser(User user)
    {
        Session session = sessionFactory.openSession();
        try
        {
            session.save(user);
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return false;
    }

    @Transactional
    public User loginUser(FormLogin formLogin)
    {
        Session session = sessionFactory.openSession();
        try
        {
            return session.createQuery("from User where username = :username " +
                            "and password = :password", User.class)
                    .setParameter("username", formLogin.getUsername())
                    .setParameter("password", formLogin.getPassword())
                    .uniqueResult();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return null;
    }

    public List<User> getAllUser()
    {
        Session session = sessionFactory.openSession();
        try
        {
            return session.createQuery("from User").list();
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return null;
    }
}
