/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories.impl;

import com.vh.pojo.AcademicTerm;
import com.vh.pojo.Course;
import com.vh.pojo.Outline;
import com.vh.pojo.OutlineTerm;
import com.vh.pojo.User;
import com.vh.repositories.OutlineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
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
public class OutlineRepositoryImpl implements OutlineRepository {

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

        Join<Outline, Course> courseJoin = r.join("course");
        Join<Outline, User> userJoin = r.join("user");
        Join<Outline, OutlineTerm> termJoind = r.join("outlineTermSet");

        String kw = params.get("kw");
        if (kw != null && !kw.isEmpty()) {
            predicates.add(b.like(r.get("course").get("name").as(String.class), "%" + kw + "%"));
//            predicates.add(b.like(courseJoin.get("name").as(String.class), "%" + kw + "%"));
        }

        String courseCredit = params.get("courseCredit");
        if (courseCredit != null && !courseCredit.isEmpty()) {
            predicates.add(b.equal(r.get("credit"), Double.parseDouble(courseCredit)));
        }

//        String teacherName = params.get("teacherName");
//        if (teacherName != null && !teacherName.isEmpty()) {
//            predicates.add(b.like(r.get("user").get("lastName").as(String.class), "%" + teacherName + "%"));
//        }
//        
//        if (teacherName != null && !teacherName.isEmpty()) {
//            predicates.add(b.like(r.get("user").get("firstName").as(String.class), "%" + teacherName + "%"));
//        }
        String teacherName = params.get("teacherName");
        if (teacherName != null && !teacherName.isEmpty()) {
            Predicate lastNamePredicate = b.like(r.get("user").get("lastName").as(String.class), "%" + teacherName + "%");
            Predicate firstNamePredicate = b.like(r.get("user").get("firstName").as(String.class), "%" + teacherName + "%");
            Predicate namePredicate = b.or(firstNamePredicate, lastNamePredicate);
            predicates.add(namePredicate);
        }

        String term = params.get("term");
        if (term != null && !term.isEmpty()) {
            predicates.add(b.like(termJoind.get("academicId").get("name").as(String.class),
                    "%" + term + "%"));
        }

        String outlineLanguage = params.get("outlineLanguage");
        if (outlineLanguage != null && !outlineLanguage.isEmpty()) {
            predicates.add(b.like(r.get("language"), String.format("%%%s%%", outlineLanguage)));
        }

        String outlineTeachMethod = params.get("outlineTeachMethod");
        if (outlineTeachMethod != null && !outlineTeachMethod.isEmpty()) {
            predicates.add(b.like(r.get("teching_method"), String.format("%%%s%%", outlineTeachMethod)));
        }

//        String courseId = params.get("courseId");
//        if (courseId != null && !courseId.isEmpty()) {
//            predicates.add(b.equal(r.get("courseId"), Integer.parseInt(courseId)));
//        }
        String userId = params.get("userId");
        if (userId != null && !userId.isEmpty()) {
//            predicates.add(b.equal(r.get("user").get("lastName").as(String.class), "%" + userId + "%"));
            predicates.add(b.like(userJoin.get("lastName").as(String.class), "%" + userId + "%"));
        }

//        String academicTermId = params.get("academicTermId");
//        if (academicTermId != null && !academicTermId.isEmpty()) {
//            predicates.add(b.equal(r.get("academicTerm"), Integer.parseInt(academicTermId)));
//        }
        q.where(predicates.toArray(Predicate[]::new));
//        q.orderBy(b.desc(r.get("id")));

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
    public void addOrUpdate(Outline o) {
        Session s = this.factory.getObject().getCurrentSession();
        if (o.getId() != null) {
            s.update(o);
        } else {
            s.save(o);
        }
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

    @Override
    public List<Outline> sreachOutlines(Map<String, String> params) {
//        String nameCourse, Integer credit, String teacherName, String term
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Outline> q = b.createQuery(Outline.class);
        Root<Outline> r = q.from(Outline.class);

        q.select(r);

        List<Predicate> predicates = new ArrayList<>();

//        Predicate predicate = b.conjunction();
//        String courseId = params.get("courseId");
//        if (courseId != null && !courseId.isEmpty()) {
//            predicates.add(b.equal(r.get("courseId"), Integer.parseInt(courseId)));
//        }
        String nameCourse = params.get("nameCourse");
        if (nameCourse != null && !nameCourse.isEmpty()) {
            predicates.add(b.like(r.get("course").get("name").as(String.class), "%" + nameCourse + "%"));
//            predicate = b.and(predicate, b.like(r.get("course").get("name").as(String.class), "%" + nameCourse + "%"));
//            predicates.add(b.like(courseJoin.get("name").as(String.class), "%" + kw + "%"));
        }

//        if (nameCourse != null && !nameCourse.isEmpty()) {
////            predicates.add(b.like(r.get("course").get("name").as(String.class), "%" + kw + "%"));
//            predicate = b.and(predicate, b.like(r.get("course").get("name").as(String.class), "%" + nameCourse + "%"));
////            predicates.add(b.like(courseJoin.get("name").as(String.class), "%" + kw + "%"));
//        }
        String credit = params.get("credit");
        if (credit != null && !credit.isEmpty()) {
            predicates.add(b.equal(r.get("credit"), Integer.parseInt(credit)));
        }
//        
//        if (credit != null){
//            predicate = b.and(predicate, b.equal(r.get("credit"), credit));
//            predicates.add(b.equal(r.get("categoryId"), Integer.parseInt(cateId)));
//        }

        String teacherName = params.get("teacherName");
        if (teacherName != null && !teacherName.isEmpty()) {
            predicates.add(b.like(r.get("user").get("lastName").as(String.class), "%" + teacherName + "%"));
        }
//        
//        if (teacherName != null && !teacherName.isEmpty()){
//            predicate = b.and(predicate, b.like(r.get("user").get("lastName").as(String.class), "%" + teacherName + "%"));
//        }

//        String term = params.get("term");
//        if (credit != null) {
//            predicates.add(b.like(r.get("outlineTermSet").get("academicId").get("name").as(String.class),
//                      "%" + term + "%"));
//        }
//        
//        if (term != null && !term.isEmpty()){
////            predicate = b.and(predicate, b.like(r.get("user").get("name").as(String.class), "%" + teacherName + "%") );
//            predicate = b.and(predicate, b.like(r.get("outlineTermSet").get("academicId").get("name").as(String.class),
//                      "%" + term + "%"));
//        }
//        q.where(predicate);
        q.where(predicates.toArray(Predicate[]::new));
        Query query = s.createQuery(q);
        List<Outline> sreachoutlines = query.getResultList();

        return sreachoutlines;
    }

}
