/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.services.AcademicTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vh.services.CourseService;
import com.vh.services.OutlineService;
import com.vh.services.OutlineTermService;
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

    @Autowired
    private CourseService courseService;
    @Autowired
    private OutlineService outlineService;
    @Autowired
    private OutlineTermService outlineTermService;
    @Autowired
    private AcademicTermService academicTermService;

//    @ModelAttribute
//    public void commonAttr(Model model){
//        model.addAttribute("academicterms", this.academicTermService.getAcademicTerms());
//    }
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("courses", this.courseService.getCourses());
        model.addAttribute("outlines", this.outlineService.getOutlines(params));
        model.addAttribute("outlineTerms", this.outlineTermService.getOutlineTerm());
        model.addAttribute("academicterms", this.academicTermService.getAcademicTerms());

        return "index";
    }
}
