/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services.impl;

import com.vh.pojo.AcademicTerm;
import com.vh.repositories.AcademicTermRepository;
import com.vh.services.AcademicTermService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DELL
 */
@Service
public class AcademicTermServiceImpl implements AcademicTermService {

    @Autowired
    private AcademicTermRepository academicTermRepo;

    @Override
    public List<AcademicTerm> getAcademicTerms() {
        return this.academicTermRepo.getAcademicTerms();
    }

}
