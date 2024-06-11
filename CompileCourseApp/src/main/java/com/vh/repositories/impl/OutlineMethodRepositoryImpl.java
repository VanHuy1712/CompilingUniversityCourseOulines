/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories.impl;

import com.vh.pojo.OutlineMethod;
import com.vh.repositories.OutlineMethodRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author DELL
 */
@Repository
@Transactional
public class OutlineMethodRepositoryImpl implements OutlineMethodRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Override
    public List<OutlineMethod> getOutlineMethods() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("OutlineMethod.findAll");
        
        return q.getResultList();
    }
    
}
