/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.formatters;

import com.vh.pojo.Course;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author DELL
 */
public class CourseFormatter implements Formatter<Course>{

    @Override
    public String print(Course course, Locale locale) {
        return String.valueOf(course.getId());
    }

    @Override
    public Course parse(String id, Locale locale) throws ParseException {
        Course c = new Course();
        c.setId(Integer.parseInt(id));
        
        return c;
    }
    
}
