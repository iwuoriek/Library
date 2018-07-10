/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service.dao;

import com.library.model.UserAccount;
import com.library.service.UserAccountService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Kelechi
 */
@Service
@Transactional
public class UserAccountServiceDao implements UserAccountService{
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public String registerUser(UserAccount user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return "success";
    }

    @Override
    public String updateUserInfo(UserAccount user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
        return "success";
    }

    @Override
    public Map<String, UserAccount> getUsers() {
        List<UserAccount> userList = sessionFactory.getCurrentSession().createQuery("From UserAccount").list();
        Map<String, UserAccount> map = new HashMap<>();
        userList.stream().forEach((user)->{map.put(user.getEmail(), user);});
        return map;
    }
    
    @Override
    public UserAccount getUser(String email) {
        UserAccount user = (UserAccount) sessionFactory.getCurrentSession()
                .createCriteria(UserAccount.class)
                .add(Restrictions.like("email", email))
                .uniqueResult();
        return user;
    }
    
}
