/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories.impl;

import com.vh.pojo.Course;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.vh.repositories.CourseRepository;

/**
 *
 * @author Huy
 */
@Repository
@Transactional
public class CourseRepositoryImpl implements CourseRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<Course> getCourses() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Course.findAll");
        return q.getResultList();
    }
    
}
