/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.services.OutlineMethodService;
import com.vh.services.OutlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("/outline/{outlineId}")
    public String detailView(Model model, @PathVariable(value = "outlineId") int id) {
        model.addAttribute("outline", this.outService.getOutlineById(id));
        model.addAttribute("outlineMethods", this.outlineMethodService.getOutlineMethods());

        return "outline";
    }
}
