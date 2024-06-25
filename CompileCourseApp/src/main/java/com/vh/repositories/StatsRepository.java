/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.repositories;

import java.util.List;

/**
 *
 * @author Huy
 */
public interface StatsRepository {
    List<Object[]> statsRevenueByOutline();
    List<Object[]> statsRevenueTeacher();
    List<Object[]> statsRevenueByPeriod(int year, String period);
}
