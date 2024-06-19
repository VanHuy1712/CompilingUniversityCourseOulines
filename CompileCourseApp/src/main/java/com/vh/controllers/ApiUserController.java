/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.components.JwtService;
import com.vh.pojo.User;
import com.vh.services.UserService;
import java.security.Principal;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Huy
 */
@RestController
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    private BCryptPasswordEncoder passswordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtService;
    
    @PostMapping(path = "/users/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] files) {
        User user = new User();
        user.setFirstName(params.get("firstName"));
        user.setLastName(params.get("lastName"));
        user.setUsername(params.get("username"));
        user.setPhone(params.get("phone"));
        user.setEmail(params.get("email"));
        String password = params.get("password");
        user.setPassword(this.passswordEncoder.encode(password));
        user.setUserRole("student");
        user.setActive(true);
        if (files.length > 0)
            user.setFile(files[0]);
        
        this.userService.addUser(user);
    }
    
    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody User user) {
        if (this.userService.authUser(user.getUsername(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUsername());
            
            return new ResponseEntity<>(token, HttpStatus.OK);
        }

        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<User> getCurrentUser(Principal p) {
        User u = this.userService.getUserByUsername(p.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}

