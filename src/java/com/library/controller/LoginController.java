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
                String sId = getEmail()+getPassword();
                setSessionId(sId);
                setEmail(null);
                return "userhome";
            }
        }
        return "login";
    }
    
    public String userLogout(){
        setSessionId(null);
        return "homepage";
    }
    
    public String getLoginOutcome(){
        String outcome;
        if(getSessionId() != null){
            outcome = userLogout();
        }else{
            outcome = userLogin();
        }
        return outcome;
    }
    
    public String getLoginLink(){
        String link;
        if(getSessionId() != null){
            link = "Logout";
        }else{
            link = "Login";
        }
        return link;
    }
    
    public String getImgSrc(){
        String src;
        if(getSessionId() != null){
            src = "resources/images/logout.png";
        }else{
            src = "resources/images/login.png";
        }
        return src;
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
     * @return the outcome
     */
    public String getOutcome() {
        return outcome;
    }

    /**
     * @param outcome the outcome to set
     */
    public void setOutcome(String outcome) {
        this.outcome = outcome;
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
     * @param imgSrc the imgSrc to set
     */
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }
}
