/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.aspect.FileUploader;
import com.library.model.UserAccount;
import com.library.service.UserAccountService;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;
import javax.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Kelechi
 */
@Named("userBean")
@RequestScoped
public class UserController extends User implements java.io.Serializable {
    private Part imageFile;
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
        System.out.println("Running method registerUser()");
        String status = userService.registerUser(user);
        if (status.equals("success")) {
            return "login";
        }
        return "registration";
    }

    public String updateUser() {
        UserAccount user = new UserAccount();
        user.setId(getId());
        user.setFirstname(getFirstname());
        user.setLastname(getLastname());
        user.setEmail(getEmail());
        user.setPassword(getPassword());
        user.setImagePath(new FileUploader().uploadProfilePic(getImageFile(), getEmail()));
        user.setUserRole(getUserRole());
        System.out.println("User role: "+getUserRole());
        userService.updateUserInfo(user);
        System.out.println("Running method updateUser()");
        return "userhome";
    }
    
    public String getUserDetails(){
        return "userDetails";
    }
    
    public String changePassword(){
        return "userhome";
    }

    public void validateEmail(FacesContext context, UIComponent comp, Object value) throws ValidatorException, NullPointerException {
        String email_ = value.toString();
        Map<String, UserAccount> userStore = userService.getUsers();
        UserAccount user = userStore.get(email_);
        System.out.println("Running method validateEmail()");
        if (user != null) {
            FacesMessage message = new FacesMessage();
            message.setSummary("This email is taken");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }

    }

    /**
     * @return the imageFile
     */
    public Part getImageFile() {
        return imageFile;
    }

    /**
     * @param imageFile the imageFile to set
     */
    public void setImageFile(Part imageFile) {
        this.imageFile = imageFile;
    }
}
