/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.vh.pojo.EvaluationMethod;
import com.vh.repositories.EvaluationMethodRepository;
import com.vh.services.EvaluationMethodService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class EvaluationMethodServiceImpl implements EvaluationMethodService{

        @Autowired
    private EvaluationMethodRepository evaluationMethodRepo;
    @Override
    public List<EvaluationMethod> getEvaluationMethods() {
        return this.evaluationMethodRepo.getEvaluationMethods();
    }
    
}
