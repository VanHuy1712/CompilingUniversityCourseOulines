/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vh.services.CourseService;
import com.vh.services.OutlineService;
import com.vh.services.UserService;
import java.util.Map;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Huy
 */
@Controller
@ControllerAdvice
public class IndexController {
//    @Autowired
//    private OutlineService outlineService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;
    
//    @ModelAttribute
//    public void commonAttr(Model model) {
//        model.addAttribute("users", this.userService.);
//    }
    
//    @ModelAttribute
//    public void commonAttr(Model model) {
//        model.addAttribute("courses", this.courseService.getCourses());
//    }
    
//    @RequestMapping("/")
//    public String index(Model model,
//            @RequestParam Map<String, String> params) {
//        
//        model.addAttribute("products", this.outlineService.getOutlines(params));
//        return "index";
//    }
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("courses", this.courseService.getCourses());
        return "index";
    }
}
