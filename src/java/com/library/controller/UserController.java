/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.utils.FileUploader;
import com.library.model.UserAccount;
import com.library.service.UserAccountService;
import com.library.utils.FacesUtil;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("userBean")
@SessionScoped
public class UserController extends User implements java.io.Serializable {
    
    private final String root = System.getProperty("user.home") + "\\Documents\\NetBeansProjects\\Library\\web\\Upload\\To\\Images\\User-Pic\\";
    @Autowired
    private UserAccountService userService;

    public UserController() {
    }

    public String registerUser() {
        UserAccount user = new UserAccount();
        user.setFirstname(getFirstname());
        user.setLastname(getLastname());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        user.setUserRole("NON-ADMIN");
        String status = userService.registerUser(user);
        if (status.equals("success")) {
            return "login";
        }
        return "registration";
    }
    
    //TO-DO Rewrite Hibernate query to update select values
    public String updateUser() {
        UserAccount user = new UserAccount();
        user.setId(getId());
        user.setFirstname(getFirstname());
        user.setLastname(getLastname());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        if(getImageFile() != null){
            user.setImageFileName(new FileUploader().imageUpload(getImageFile()));
        } else {
            user.setImageFileName(getImageFileName());
        }
        user.setImageFileName(new FileUploader().imageUpload(getImageFile()));
        user.setUserRole(getUserRole());
        userService.updateUserInfo(user);
        return "userhome";
    }
    
    //TO-DO add password reset function
    public String changePassword(){
        return "userhome";
    }

    public void validateEmail(FacesContext context, UIComponent comp, Object value) throws ValidatorException, NullPointerException {
        String email = value.toString();
        Map<String, UserAccount> userStore = userService.getUsers();
        UserAccount user = userStore.get(email);
        if (user != null) {
            FacesMessage message = new FacesMessage();
            message.setSummary("This email is taken");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
    
    public String doLogin() {
        UserAccount user = userService.getUser(getEmail());
        if (user != null) {
            if (getPassword().compareTo(user.getPassword()) == 0) {
                setUser(user);
                HttpSession session = FacesUtil.getSession();
                session.setAttribute("userId", user.getId());
                if(user.getImageFileName() != null){
                    session.setAttribute("image", root + user.getImageFileName());
                } else {
                    session.setAttribute("image", root + "default.png");
                }
                if (user.getUserRole().equals("NON-ADMIN")){
                    return "userhome?faces-redirect=true";
                } else if (user.getUserRole().equals("ADMIN")){
                    return "adminhome?faces-redirect=true";
                }
            }
        }
        return "login";
    }

    public String doLogout() {
        HttpSession session = FacesUtil.getSession();
        session.invalidate();
        return "login?faces-redirect=true";
    }
    
    public void setUser(UserAccount user){
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setEmail(user.getEmail());
        setImageFileName(user.getImageFileName());
        setUserRole(user.getUserRole());
        setPassword(user.getPassword());
    }

}
