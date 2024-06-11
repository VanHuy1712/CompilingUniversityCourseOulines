/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.vh.pojo.OutlineTerm;
import com.vh.repositories.OutlineTermRepository;
import com.vh.services.OutlineTermService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class OutlineTermServiceImpl implements OutlineTermService{

    @Autowired
    private OutlineTermRepository outlineTermRepo;
    
    @Override
    public List<OutlineTerm> getOutlineTerm() {
        return this.outlineTermRepo.getOutlineTerm();
    }
    
}
