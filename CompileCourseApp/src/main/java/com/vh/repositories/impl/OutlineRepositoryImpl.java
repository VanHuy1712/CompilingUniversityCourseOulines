/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories.impl;

import com.vh.pojo.Outline;
import com.vh.repositories.OutlineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Huy
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class OutlineRepositoryImpl implements OutlineRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Outline> getOutlines(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Outline> q = b.createQuery(Outline.class);
        Root r = q.from(Outline.class);
        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("title"), String.format("%%%s%%", kw)));
        }
        
        String courseCredit = params.get("courseCredit");
        if (courseCredit != null && !courseCredit.isEmpty()) {
            predicates.add(b.equal(r.get("credit"), Double.parseDouble(courseCredit)));
        }
        
        String outlineLanguage = params.get("outlineLanguage");
        if (outlineLanguage != null && !outlineLanguage.isEmpty()) {
            predicates.add(b.like(r.get("language"), String.format("%%%s%%", outlineLanguage)));
        }
        
        String outlineTeachMethod = params.get("outlineTeachMethod");
        if (outlineTeachMethod != null && !outlineTeachMethod.isEmpty()) {
            predicates.add(b.like(r.get("teching_method"), String.format("%%%s%%", outlineTeachMethod)));
        }

        String courseId = params.get("courseId");
        if (courseId != null && !courseId.isEmpty()) {
            predicates.add(b.equal(r.get("courseId"), Integer.parseInt(courseId)));
        }
        
        String userId = params.get("userId");
        if (userId != null && !userId.isEmpty()) {
            predicates.add(b.equal(r.get("userId"), Integer.parseInt(userId)));
        }
        
        String academicTermId = params.get("academicTermId");
        if (academicTermId != null && !academicTermId.isEmpty()) {
            predicates.add(b.equal(r.get("academicTermId"), Integer.parseInt(academicTermId)));
        }

        q.where(predicates.toArray(Predicate[]::new));
        q.orderBy(b.desc(r.get("id")));

        Query query = s.createQuery(q);
        
        
        String p = params.get("page");
        if (p != null && !p.isEmpty()) {
            int pageSize = Integer.parseInt(env.getProperty("outlines.pageSize").toString());
            int start = (Integer.parseInt(p) - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize);
        }
        
        List<Outline> outlines = query.getResultList();

        return outlines;
    }

    @Override
    public void addOrUpdate(Outline p) {
        Session s = this.factory.getObject().getCurrentSession();
        if (p.getId() != null)
            s.update(p);
        else
            s.save(p);
    }

    @Override
    public Outline getOutlineById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Outline.class, id);
    }

    @Override
    public void deleteOutline(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Outline p = this.getOutlineById(id);
        s.delete(p);
    }
    
}
