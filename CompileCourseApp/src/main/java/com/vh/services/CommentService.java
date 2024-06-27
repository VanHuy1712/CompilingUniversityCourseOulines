/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services;

import com.vh.pojo.Comment;
import com.vh.pojo.Outline;
import com.vh.pojo.User;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Huy
 */
public interface CommentService {
    List<Comment> getComments();
    Comment getCommentById(int id);
    boolean addCommentToOutline(Map<String, Outline> comments);
    void addComment(Comment c);
    void createComment(User userId, Outline outlineId, String content);
}
