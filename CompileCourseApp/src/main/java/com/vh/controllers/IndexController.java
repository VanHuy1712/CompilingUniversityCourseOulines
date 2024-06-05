/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.services.OutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Huy
 */
@Controller
@ControllerAdvice
public class IndexController {
    @Autowired
    private OutlineService outlineService;
    
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("outlines", this.outlineService.getOutlines());
        return "index";
    }
}
