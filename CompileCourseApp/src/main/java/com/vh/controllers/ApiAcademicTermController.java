/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.pojo.AcademicTerm;
import com.vh.pojo.Course;
import com.vh.services.AcademicTermService;
import com.vh.services.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Huy
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiAcademicTermController {
    @Autowired
    private AcademicTermService ATermService;
    
    @GetMapping("/academicterms/")
    public ResponseEntity<List<AcademicTerm>> list() {
        return new ResponseEntity<>(this.ATermService.getAcademicTerms(), HttpStatus.OK);
    }
}
