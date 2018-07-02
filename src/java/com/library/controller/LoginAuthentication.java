/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.UserAccount;
import com.library.service.LoginAuthenticationService;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("loginBean")
@SessionScoped
public class LoginAuthentication implements java.io.Serializable {

    private String email;
    private String password;
    @Autowired
    private LoginAuthenticationService loginService;

    public LoginAuthentication() {
    }

    public String authenticateUser() {
        UserAccount user = loginService.getUser(getEmail());
        if (user != null) {
            if (getPassword().compareTo(user.getPassword()) == 0) {
                UserRegistration ur = new UserRegistration();
                ur.setFirstname("Kenneth");
                ur.setLastname("Mike");
                ur.setEmail("kenmike@gmail.com");
                return "userhome";
            }
        }
        return "login";
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
