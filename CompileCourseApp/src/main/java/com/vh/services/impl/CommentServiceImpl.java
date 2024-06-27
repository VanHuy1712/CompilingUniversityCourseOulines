/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.vh.pojo.Comment;
import com.vh.pojo.Outline;
import com.vh.pojo.User;
import com.vh.repositories.CommentRepository;
import com.vh.services.CommentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Huy
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepo;
    
    @Override
    public List<Comment> getComments() {
        return this.commentRepo.getComments();
    }

    @Override
    public Comment getCommentById(int id) {
        return this.commentRepo.getCommentById(id);
    }
    
    @Override
    public void addComment(Comment c) {
        
        this.commentRepo.addComment(c);
        
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean addCommentToOutline(Map<String, Outline> comments){
        return this.commentRepo.addCommentToOutline(comments);
    }

    @Override
    public void createComment(User userId, Outline outlineId, String content) {
        this.commentRepo.createComment(userId, outlineId, content);
    }
}
