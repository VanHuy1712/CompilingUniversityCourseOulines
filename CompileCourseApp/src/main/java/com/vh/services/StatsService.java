/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.services;

import java.util.List;

/**
 *
 * @author Huy
 */
public interface StatsService {
    List<Object[]> statsRevenueByOutline();
    List<Object[]> statsRevenueByPeriod(int year, String period);
}
