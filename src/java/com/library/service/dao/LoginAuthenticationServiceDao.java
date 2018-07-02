/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service.dao;

import com.library.model.UserAccount;
import com.library.service.LoginAuthenticationService;
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
public class LoginAuthenticationServiceDao implements LoginAuthenticationService{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public UserAccount getUser(String email) {
        UserAccount user = (UserAccount) sessionFactory.getCurrentSession()
                .createCriteria(UserAccount.class)
                .add(Restrictions.like("email", email))
                .uniqueResult();
        return user;
    }
    
}
