/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vh.pojo.Outline;
import com.vh.repositories.OutlineRepository;
import com.vh.services.OutlineService;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Huy
 */
@Service
public class OutlineServiceImpl implements OutlineService{
    @Autowired
    private OutlineRepository outlineRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Outline> getOutlines(Map<String, String> params) {
        return this.outlineRepo.getOutlines(params);
    }

    @Override
    public void addOrUpdate(Outline o) {
//        if (!o.getFile().isEmpty()) {
//            try {
//                Map res = this.cloudinary.uploader().upload(o.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
////                o.setImage(res.get("secure_url").toString());
//            } catch (IOException ex) {
//                Logger.getLogger(OutlineServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        
        this.outlineRepo.addOrUpdate(o);
    }

    @Override
    public Outline getOutlineById(int id) {
        return this.outlineRepo.getOutlineById(id);
    }

    @Override
    public void deleteOutline(int id) {
        this.outlineRepo.deleteOutline(id);
    }

//    @Override
//    public List<Outline> sreachOutlines(String nameCourse, Integer credit, String teacherName, String term) {
//       return this.outlineRepo.sreachOutlines(nameCourse, credit, teacherName, term);
//    }

    @Override
    public List<Outline> sreachOutlines(Map<String, String> params) {
        return this.outlineRepo.sreachOutlines(params);
    }
}
