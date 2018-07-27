/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.controller;

import com.library.model.SecurityQuestion;
import com.library.utils.FileUploader;
import com.library.model.UserAccount;
import com.library.service.UserAccountService;
import com.library.utils.FacesUtil;
import com.library.utils.GenerateId;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
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

    private final String root = System.getProperty("user.home") + "\\Upload\\To\\Images\\User-Pic\\";
    @Autowired
    private UserAccountService userService;

    public UserController() {
    }

    public String registerUser() {
        UserAccount user = new UserAccount();
        user.setId(new GenerateId().generateUserId(userService.getAllUsers()));
        user.setFirstname(getFirstname());
        user.setLastname(getLastname());
        user.setEmail(getEmail());
        user.setQuestion(getQuestion());
        user.setAnswer(getAnswer());
        user.setPassword(getPassword());
        user.setUserRole("NON-ADMIN");
        if (userService.getUser(user.getEmail()) == null){
            userService.registerUser(user);
            return "login";
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "This user already exists");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return "registration";
    }

    public String updateUser() {
        UserAccount user = new UserAccount();
        user.setId(getId());
        user.setFirstname(getFirstname());
        user.setLastname(getLastname());
        user.setEmail(getEmail());
        if (getImageFile() != null) {
            user.setImageFileName(new FileUploader().imageUpload(getImageFile()));
            FacesUtil.getSession().removeAttribute("imageUrl");
            FacesUtil.getSession().setAttribute("imageUrl", root + user.getImageFileName());
        } else {
            user.setImageFileName(getImageFileName());
        }
        user.setUserRole(getUserRole());
        userService.updateUserInfo(user);
        setUpdatedUser(user);
        return doRedirect(user);
    }

    public String doPasswordChange() {
        UserAccount user = userService.getUser(getEmail());
        if (user != null && user.getAnswer().equalsIgnoreCase(getAnswer())) {
            userService.updatePassword(getEmail(), getPassword());
            return doLogout();
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Incorrect answer!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "passwordChange";
        }
    }

    public void emailChange(ValueChangeEvent event){
        String email = event.getNewValue().toString();
        UserAccount user = userService.getUser(email);
        if(user != null){
            setQuestion(user.getQuestion());
        } else {
            setQuestion(null);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error", "Invalid user email!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void validateEmail(FacesContext context, UIComponent comp, Object value) throws ValidatorException, NullPointerException {
        String email = value.toString();
        UserAccount user = userService.getUser(email);
        if (user != null) {
            FacesMessage message = new FacesMessage();
            message.setSummary("This user already exists");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

    public String doLogin() {
        UserAccount user = userService.getUser(getEmail());
        if (user != null) {
            if (getPassword().compareTo(user.getPassword()) == 0) {
                setLoggedInUser(user);
                HttpSession session = FacesUtil.getSession();
                session.setAttribute("userId", user.getId());
                session.setAttribute("role", user.getUserRole());
                session.setAttribute("user", user);
                if (user.getImageFileName() != null) {
                    session.setAttribute("imageUrl", root + user.getImageFileName());
                } else {
                    session.setAttribute("imageUrl", root + "default.png");
                }
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "Your password is incorrect!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "login";
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Error!", "User does not exist!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "login";
        }
        return doRedirect(user);
    }

    public String doLogout() {
        HttpSession session = FacesUtil.getSession();
        session.invalidate();
        UserAccount user = new UserAccount();
        setLoggedInUser(user);
        return "login?faces-redirect=true";
    }

    public void setLoggedInUser(UserAccount user) {
        setId(user.getId());
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setEmail(user.getEmail());
        setImageFileName(user.getImageFileName());
        setUserRole(user.getUserRole());
        setPassword(user.getPassword());
        setQuestion(user.getQuestion());
        setAnswer(null);
    }

    public void setUpdatedUser(UserAccount user) {
        setId(user.getId());
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setEmail(user.getEmail());
        setImageFileName(user.getImageFileName());
    }

    public String doRedirect(UserAccount user) {
        String content = new String();
        try {
            if (user.getUserRole().equals("NON-ADMIN")) {
               content = "userhome?faces-redirect=true";
            } else if (user.getUserRole().equals("ADMIN")) {
                content = "adminhome?faces-redirect=true";
            }
        } catch (NullPointerException e) {
            return "login";
        }
        return content;
    }

    public List<SecurityQuestion> getQuestions() {
        return userService.getQuestions();
    }

    public String optionToDisplay() {
        if (FacesUtil.getUserId() == null) {
            return "Login";
        } else {
            return "Logout";
        }
    }

    public String imageToDisplay() {
        if (FacesUtil.getUserId() == null) {
            return "login.png";
        } else {
            return "logout.png";
        }
    }

    public String actionToPerform() {
        if (FacesUtil.getUserId() == null) {
            return "login?faces-redirect=true";
        } else {
            return doLogout();
        }
    }

    public String toUserHome() {
        if (FacesUtil.getUserId() != null) {
            if (getUserRole().equals("NON-ADMIN")) {
                return "userhome?faces-redirect=true";
            } else if (getUserRole().equals("ADMIN")) {
                return "adminhome?faces-redirect=true";
            }
        }
        return "#";
    }

    public String linkToDisplay() {
        if (FacesUtil.getUserId() != null) {
            return getFirstname() + " " + getLastname();
        }
        return "";
    }

}
