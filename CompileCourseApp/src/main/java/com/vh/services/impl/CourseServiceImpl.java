/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.vh.pojo.Course;
import com.vh.pojo.Outline;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vh.repositories.CourseRepository;
import com.vh.services.CourseService;

/**
 *
 * @author Huy
 */
@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseRepository courseRepo;
    
    @Override
    public List<Course> getCourses() {
        return this.courseRepo.getCourses();
    }
    
}
