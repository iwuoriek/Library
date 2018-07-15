/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.UserAccount;
import com.library.service.LoginAuthenticationService;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("loginBean")
@SessionScoped
public class LoginController extends User implements java.io.Serializable {

    private String loginMessage;
    @Autowired
    private LoginAuthenticationService loginService;

    public LoginController() {
    }

    public String doLogin() {
        UserAccount user = loginService.getUser(getEmail());
        if (user != null) {
            System.out.println("USER-ROLE: "+user.getUserRole());
            if (getPassword().compareTo(user.getPassword()) == 0) {
//                if(user.getImagePath()!= null){
//                    this.setImagePath(user.getImagePath());
//                } else {
//                    this.setImagePath("resources/images/images.png");
//                }
                if (user.getUserRole().equals("NON-ADMIN")){
                    return "userhome?faces-redirect=true";
                } else if (user.getUserRole().equals("ADMIN")){
                    return "adminhome?faces-redirect=true";
                }
            }
        }
        setLoginMessage("Incorrect username or password!");
        return "login";
    }

    public String doLogout() {
        return "login?faces-redirect=true";
    }

    /**
     * @return the loginMessage
     */
    public String getLoginMessage() {
        return loginMessage;
    }

    /**
     * @param loginMessage the loginMessage to set
     */
    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
}
