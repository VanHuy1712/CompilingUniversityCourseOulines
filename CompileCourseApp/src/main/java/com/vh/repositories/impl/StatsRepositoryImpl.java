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
import com.vh.repositories.StatsRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Huy
 */
@Repository
@Transactional
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> statsRevenueByOutline() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rO = q.from(Outline.class);
        Root rC = q.from(Course.class);

//        q.multiselect(rO.get("id"), rC.get("name"), rC.get("id"));
//
//        List<Predicate> predicates = new ArrayList<>();
//        predicates.add(b.equal(rO.get("course"), rC.get("id")));
//
//        q.where(predicates.toArray(Predicate[]::new));
//
//        q.groupBy(rO.get("id"));
//
//        Query query = s.createQuery(q);
//        return query.getResultList();
        // Sử dụng hàm count để đếm số lượng đề cương
        q.multiselect(rC.get("name"), b.count(rO.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rO.get("course"), rC.get("id")));

        q.where(predicates.toArray(new Predicate[0]));

        // Nhóm theo tên khóa học
        q.groupBy(rC.get("name"));

        Query query = s.createQuery(q);
        return query.getResultList();

    }

    @Override
    public List<Object[]> statsRevenueByPeriod(int year, String period) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rOT = q.from(OutlineTerm.class);
        Root rO = q.from(Outline.class);

        q.multiselect(b.function(period, Integer.class, rO.get("createDate")), rOT.get("academicId"));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rOT.get("outlineId"), rO.get("id")));
        predicates.add(b.equal(b.function("YEAR", Integer.class, rO.get("createDate")), year));

        q.where(predicates.toArray(Predicate[]::new));
        q.groupBy(b.function(period, Integer.class, rO.get("createDate")));

        Query query = s.createQuery(q);
        return query.getResultList();

    }

    @Override
    public List<Object[]> statsRevenueTeacher() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root rO = q.from(Outline.class);
        Root rT = q.from(User.class);
        
        Expression<String> fullName = b.concat(rT.get("lastName"), b.concat(" ", rT.get("firstName")));

        q.multiselect(fullName, b.count(rO.get("id")));

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(rO.get("user"), rT.get("id")));

        q.where(predicates.toArray(new Predicate[0]));

        // Nhóm theo tên khóa học
        q.groupBy(fullName);

        Query query = s.createQuery(q);
        return query.getResultList();
    }

}
