/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.services.StatsService;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Huy
 */
@Controller
public class StatsController {
    @Autowired
    private StatsService statsService;
    @GetMapping("/stats")
    public String stats(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("statsRevenue", this.statsService.statsRevenueByOutline());
        
        String peroid = params.getOrDefault("peroid", "MONTH");
        String year = params.getOrDefault("year", String.valueOf(LocalDate.now().getYear()));
        model.addAttribute("statsRevenueByPeroid", 
                this.statsService.statsRevenueByPeriod(Integer.parseInt(year), peroid));
        
        return "stats";
    }
}
