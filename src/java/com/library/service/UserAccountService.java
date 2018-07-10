/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.library.service;

import com.library.model.UserAccount;
import java.util.Map;

/**
 *
 * @author Kelechi
 */
public interface UserAccountService {
    public String registerUser(UserAccount user);
    
    public String updateUserInfo(UserAccount user);
    
    public Map<String, UserAccount> getUsers();
    
    public UserAccount getUser(String email);
}
