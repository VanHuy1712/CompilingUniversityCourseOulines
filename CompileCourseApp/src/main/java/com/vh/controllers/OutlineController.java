/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.pojo.Outline;
import com.vh.pojo.OutlineMethod;
import com.vh.pojo.OutlineTerm;
import com.vh.services.OutlineMethodService;
import com.vh.services.OutlineService;
import com.vh.services.UserService;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DELL
 */
@Controller
public class OutlineController {

    @Autowired
    private OutlineService outService;

    @Autowired
    private OutlineMethodService outlineMethodService;

    @Autowired
    private UserService userService;

    @GetMapping("/outline/{outlineId}")
    public String detailView(Model model, @PathVariable(value = "outlineId") int id) {
        model.addAttribute("outline", this.outService.getOutlineById(id));
        model.addAttribute("outlineMethods", this.outlineMethodService.getOutlineMethods());

        return "outline-detail";
    }

    @GetMapping("/outlines/{outlineId}")
    public String uploadOutlineView(Model model, @PathVariable(value = "outlineId") int id) {
        model.addAttribute("compilation", this.outService.getOutlineById(id));

        return "outline-list";
    }

    @GetMapping("/outlines")
    public String addOutlineView(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("outline", this.outService.getOutlines(params));

        Outline outline = new Outline();
        outline.getOutlineTermSet().add(new OutlineTerm());
        for (int i = 0; i < 3; i++) {
            outline.getOutlineMethodSet().add(new OutlineMethod());
        }
        model.addAttribute("compilation", outline);

        return "outline-list";
    }

    @PostMapping("/outlines")
    public String addOutlineProcess(Model model, @ModelAttribute(value = "compilation") @Valid Outline o,
            BindingResult rs) {

        if (!rs.hasErrors()) {
            try {
                o.setCreateDate(new Date()); // Đặt ngày hiện tại
                o.setUser(userService.getUserById(1));
                
                for (OutlineTerm term : o.getOutlineTermSet()) {
                    term.setCreatedDate(new Date());
                    term.setOutlineId(o);
                }
                for (OutlineMethod method : o.getOutlineMethodSet()) {
                    if(method.getEvaluationMethod()!= null)
                        method.setOutline(o);
                }
                this.outService.addOrUpdate(o);

                return "redirect:/";
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        return "outline-list";
    }

//    @RequestMapping("/sreachoutlines")
//    public String sreachOutlineDetail(Model model, @RequestParam Map<String, String> params){
//        
//        model.addAttribute("soutlines", this.outService.sreachOutlines(params));
//        
//        return "sreachoutlines";
//    }
//    public String sreachOutlineDetail(Model model,
//            @RequestParam(required = false) String nameCourse,
//            @RequestParam(required = false) Integer credit,
//            @RequestParam(required = false) String teacherName,
//            @RequestParam(required = false) String term) {
//
//        List<Outline> outlines = this.outService.sreachOutlines(nameCourse, credit, teacherName, term);
//        model.addAttribute("soutlines", outlines);
////        model.addAttribute("soutlines", this.outService.sreachOutlines(nameCourse, Integer.SIZE, teacherName, term));
//
//        return "sreachoutlines";
//    }
}
