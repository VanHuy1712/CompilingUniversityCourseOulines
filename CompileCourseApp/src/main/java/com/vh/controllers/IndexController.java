/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy
 */
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
