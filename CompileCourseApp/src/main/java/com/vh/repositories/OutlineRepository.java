/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories;

import com.vh.pojo.Outline;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Huy
 */
public interface OutlineRepository {
    List<Outline> getOutlines(Map<String, String> params);
//    List<Outline> sreachOutlines(String nameCourse, Integer credit, String teacherName, String term);
    List<Outline> sreachOutlines(Map<String, String> params);

    void addOrUpdate(Outline o);
    Outline getOutlineById(int id);
    void deleteOutline(int id);
}
