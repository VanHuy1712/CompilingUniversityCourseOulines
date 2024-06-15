/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.pojo.Outline;
import com.vh.services.OutlineService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Huy
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiOutlineController {
    @Autowired
    private OutlineService outlineService;
    
    @GetMapping("/outlines/")
    public ResponseEntity<List<Outline>> list(@RequestParam  Map params) {
        return new ResponseEntity<>(this.outlineService.getOutlines(params), HttpStatus.OK);
    }
    
    @GetMapping(path = "/outlines/{outlineId}/", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Outline> retrieve(@PathVariable(value = "outlineId") int id) {
        return new ResponseEntity<>(this.outlineService.getOutlineById(id), HttpStatus.OK);
    }
    
    @DeleteMapping("/outlines/{outlineId}/")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable(value = "outlineId") int id) {
        this.outlineService.deleteOutline(id);
    }
    
//    @GetMapping("/sreachoutlines/")
//    public ResponseEntity<List<Outline>> sreachOutlines(
//            @RequestParam(required = false) String nameCourse,
//            @RequestParam(required = false) Integer credit,
//            @RequestParam(required = false) String teacherName,
//            @RequestParam(required = false) String term){
//        List<Outline> outlines = this.outlineService.sreachOutlines(nameCourse, credit, teacherName, term);
//        if(outlines == null || outlines.isEmpty()){
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//    public ResponseEntity<List<Outline>> listSreach(@RequestParam  Map params) {
//        return new ResponseEntity<>(this.outlineService.sreachOutlines(params), HttpStatus.OK);
//    }
}
