/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories.impl;

import com.vh.pojo.Comment;
import com.vh.pojo.Outline;
import com.vh.pojo.User;
import com.vh.repositories.CommentRepository;
import com.vh.repositories.OutlineRepository;
import com.vh.repositories.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Huy
 */
@Repository
@Transactional
public class CommentRepositoryImpl implements CommentRepository {
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private OutlineRepository outlineRepo;

    @Override
    public List<Comment> getComments() {
//        Session s = this.factory.getObject().getCurrentSession();
//        Query query = s.createQuery("From Comment WHERE outline=:outlineId");
//        query.setParameter("outlineId", outlineId);
//        
//        return query.getResultList();

        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Comment.findAll");

        return q.getResultList();
    }

    @Override
    public Comment getCommentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
       return s.get(Comment.class, id);
    }
    
    @Override
    public void addComment(Comment c) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(c);
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addCommentToOutline(Map<String, Outline> comments){
        Session s = this.factory.getObject().getCurrentSession();
        Comment c = new Comment();
        
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User u = this.userRepo.getUserByUsername(authentication.getName());
            c.setUser(u);

            for (Outline o : comments.values()) {
                
                c.setOutline(this.outlineRepo.getOutlineById(o.getId()));
                c.setContent(c.getContent());

                s.save(c);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }
//    public boolean addReceipt(Map<String, Cart> carts) {
//
//        Session s = this.factory.getObject().getCurrentSession();
//        SaleOrder order = new SaleOrder();
//
//        try {
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            User u = this.userRepo.getUserByUsername(authentication.getName());
//            order.setUserId(u);
//            order.setCreatedDate(new Date());
//            s.save(u);
//
//            for (Cart c : carts.values()) {
//                OrderDetail d = new OrderDetail();
//                d.setProductId(this.productRepo.getProductById(c.getId()));
//                d.setOrderId(order);
//                d.setQuantity(c.getQuantity());
//                d.setUnitPrice(c.getPrice());
//
//                s.save(d);
//            }
//
//            return true;
//        } catch (HibernateException ex) {
//            ex.printStackTrace();
//            return false;
//        }
//
//    }

    @Override
    public void createComment(User userId, Outline outlineId, String content) {
        Session s = this.factory.getObject().getCurrentSession();
        
        Comment c = new Comment();
        c.setUser(userId);
        c.setOutline(outlineId);
        c.setContent(content);
        c.setDayPost(new Date());
        
        s.save(c);
    }
}
