/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.pojo.Comment;
import com.vh.pojo.Course;
import com.vh.pojo.Outline;
import com.vh.pojo.User;
import com.vh.services.CommentService;
import com.vh.services.OutlineService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Huy
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApiCommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private OutlineService outlineService;

//    @GetMapping(path = "/outlines/{outlineId}/comments/", produces = {MediaType.APPLICATION_JSON_VALUE})
//    public ResponseEntity<Comment> retrieve(@PathVariable(value = "outlineId") int id) {
//        return new ResponseEntity<>(this.commentService.getCommentById(id), HttpStatus.OK);
//    }

    @GetMapping("/comments/")
    public ResponseEntity<List<Comment>> list() {
        return new ResponseEntity<>(this.commentService.getComments(), HttpStatus.OK);
    }
    
    @PostMapping(path = "/comments/")
    @CrossOrigin
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@RequestBody Comment comments) {
        String content = comments.getContent();
        Outline outlineId = comments.getOutline();
        User userId = comments.getUser();

        if (content.isBlank() || content.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }

        this.commentService.createComment(userId, outlineId, content);
        return ResponseEntity.ok().body(null);
    }
}
