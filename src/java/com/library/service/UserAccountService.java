/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service;

import com.library.model.SecurityQuestion;
import com.library.model.UserAccount;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kelechi
 */
public interface UserAccountService {
    public String registerUser(UserAccount user);
    
    public String updateUserInfo(UserAccount user);
    
    public Map<String, UserAccount> getUsers();
    
    public List<UserAccount> getAllUsers();
    
    public UserAccount getUser(String email);
    
    public List<SecurityQuestion> getQuestions();
    
    public String updatePassword(String email, String password);
}
