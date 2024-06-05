/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.vh.pojo.Outline;
import com.vh.repositories.OutlineRepository;
import com.vh.services.OutlineService;
import java.util.List;
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
    
    @Override
    public List<Outline> getOutlines() {
        return this.outlineRepo.getOutlines();
    }
    
}
