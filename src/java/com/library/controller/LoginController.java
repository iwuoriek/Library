/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.aspect.Util;
import com.library.model.UserAccount;
import com.library.service.LoginAuthenticationService;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("loginBean")
@SessionScoped
public class LoginController implements java.io.Serializable {

    private String email;
    private String password;
    private String sessionId;
    private String outcome;
    private String linkName;
    private String imgSrc;

    @Autowired
    private LoginAuthenticationService loginService;

    public LoginController() {
    }

    public String userLogin() {
        UserAccount user = loginService.getUser(getEmail());
        if (user != null) {
            if (getPassword().compareTo(user.getPassword()) == 0) {
                if (user.getUserRole().equals("NON-ADMIN")){
                    HttpSession session = Util.getSession();
                    session.setAttribute("user", user);
                    System.out.println("Running method userLogin()");
                    System.out.println("Session ID: "+session.getId());
                    System.out.println("User: "+user.toString());
                    return "userhome";
                } else if (user.getUserRole().equals("ADMIN")){
                    return "adminhome";
                }
            }
        }
        return "login?faces-redirect=true";
    }

    public String userLogout() {
        HttpSession session = Util.getSession();
        session.invalidate();
        System.out.println("Running method userLogout()");
        return "login?faces-redirect=true";
    }
    
    public String actionOutCome(){
        if(sessionId == null){
            return userLogin();
        }else{
            return userLogout();
        }
    }

//    public String getLoginOutcome(){
//        String outcome = "login";
//        if(getSessionId() != null){
//            outcome = "homepage";
//        }
//        
//        return outcome;
//    }
//    
//    public String getLinkName(){
//        String link;
//        if(getSessionId() != null){
//            link = "Logout";
//        }else{
//            link = "Login";
//        }
//        return link;
//    }
//    
//    public String getImgSrc(){
//        String src;
//        if(getSessionId() != null){
//            src = "resources/images/logout.png";
//        }else{
//            src = "resources/images/login.png";
//        }
//        return src;
//    }
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

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    /**
     * @return the linkName
     */
    public String getLinkName() {
        return linkName;
    }

    /**
     * @param linkName the linkName to set
     */
    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    /**
     * @return the imgSrc
     */
    public String getImgSrc() {
        return imgSrc;
    }

    /**
     * @param imgSrc the imgSrc to set
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
