/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services;

import com.vh.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Huy
 */
public interface UserService extends UserDetailsService{
    User getUserByUsername(String username);
    void addUser(User user);
    boolean authUser(String username, String password);
}
