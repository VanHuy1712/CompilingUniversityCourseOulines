/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories;

import com.vh.pojo.User;

/**
 *
 * @author Huy
 */
public interface UserRepository {
    User getUserByUsername(String username);
    void addUser(User user);
}
