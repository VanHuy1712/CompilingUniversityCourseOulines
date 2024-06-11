/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.vh.pojo.OutlineMethod;
import com.vh.repositories.OutlineMethodRepository;
import com.vh.services.OutlineMethodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class OutlineMethodServiceImpl implements OutlineMethodService{

    @Autowired
    private OutlineMethodRepository outlineMethodRepo;
    
    @Override
    public List<OutlineMethod> getOutlineMethods() {
        return this.outlineMethodRepo.getOutlineMethods();
    }
    
}
