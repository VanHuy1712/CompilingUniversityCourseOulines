/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vh.controllers;

import com.vh.pojo.Outline;
import com.vh.pojo.OutlineMethod;
import com.vh.pojo.OutlineTerm;
import com.vh.pojo.User;
import com.vh.services.OutlineMethodService;
import com.vh.services.OutlineService;
import com.vh.services.UserService;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author DELL
 */
@Controller
public class OutlineController {

    @Autowired
    private OutlineService outService;

    @Autowired
    private OutlineMethodService outlineMethodService;

    @Autowired
    private UserService userService;

    @GetMapping("/outline/{outlineId}")
    public String detailView(Model model, @PathVariable(value = "outlineId") int id) {
        model.addAttribute("outline", this.outService.getOutlineById(id));
        model.addAttribute("outlineMethods", this.outlineMethodService.getOutlineMethods());

        return "outline-detail";
    }

    @GetMapping("/outlines/{outlineId}")
    public String uploadOutlineView(Model model, @PathVariable(value = "outlineId") int id) {
        model.addAttribute("compilation", this.outService.getOutlineById(id));

        return "outline-list";
    }

    @GetMapping("/outlines")
    public String addOutlineView(Model model, @RequestParam Map<String, String> params) {
        model.addAttribute("outline", this.outService.getOutlines(params));

        Outline outline = new Outline();
        outline.getOutlineTermSet().add(new OutlineTerm());
        for (int i = 0; i < 3; i++) {
            outline.getOutlineMethodSet().add(new OutlineMethod());
        }
        model.addAttribute("compilation", outline);

        return "outline-list";
    }

    @PostMapping("/outlines")
    public String addOutlineProcess(Model model, @ModelAttribute("compilation") @Valid Outline o,
            BindingResult rs, Principal principal) {

        if (rs.hasErrors() || hasValidationErrors(o, rs)) {
            return "outline-list";

        }
        double totalWeight = o.getOutlineMethodSet().stream()
                .mapToDouble(OutlineMethod::getWeight)
                .sum();

        if (Double.compare(totalWeight, 100.0) != 0) {
            rs.rejectValue("outlineMethodSet", "error.outlineMethodSet", "Tổng tỷ lệ phải bằng 100.");
            return "outline-list";
        }
//        if (hasOutlineMethodValidationErrors(o, rs)) {
//            return "outline-list";
//        }

        try {
            o.setCreateDate(new Date());
            String username = principal.getName();
            User currentUser = userService.getUserByUsername(username);
            o.setUser(currentUser);

            for (OutlineTerm term : o.getOutlineTermSet()) {
                term.setCreatedDate(new Date());
                term.setOutlineId(o);
            }

            for (OutlineMethod method : o.getOutlineMethodSet()) {
                method.setOutline(o);
            }

            this.outService.addOrUpdate(o);

            return "redirect:/";
        } catch (Exception ex) {
            rs.reject("error.general", "Đã xảy ra lỗi khi lưu dữ liệu.");
            return "outline-list";
        }
    }

    /**
     * Kiểm tra các lỗi validation cho outlineTermSet trong Outline.
     */
    private boolean hasValidationErrors(Outline outline, BindingResult rs) {
        boolean hasErrors = false;
        for (int i = 0; i < outline.getOutlineTermSet().size(); i++) {
            OutlineTerm term = outline.getOutlineTermSet().get(i);
            if (term.getAcademicId() == null || term.getAcademicId().getId() == null) {
                rs.rejectValue("outlineTermSet[" + i + "].academicId.id", "error.outlineTermSet.academicId", "Vui lòng chọn khóa học.");
                hasErrors = true;
            }
        }
        return hasErrors;
    }

    /**
     * Kiểm tra các lỗi validation cho OutlineMethod trong outlineMethodSet của
     * Outline.
     */
//    private boolean hasOutlineMethodValidationErrors(Outline outline, BindingResult rs) {
//        boolean hasErrors = false;
//        for (int i = 0; i < outline.getOutlineMethodSet().size(); i++) {
//            OutlineMethod method = outline.getOutlineMethodSet().get(i);
//            if (StringUtils.isBlank(method.getName())) {
//                rs.rejectValue("outlineMethodSet[" + i + "].name", "error.outlineMethod.name", "Tên không được để trống.");
//                hasErrors = true;
//            }
//            if (method.getWeight() == null || method.getWeight() <= 0) {
//                rs.rejectValue("outlineMethodSet[" + i + "].weight", "error.outlineMethod.weight", "Tỷ lệ phải là số dương.");
//                hasErrors = true;
//            }
//            if (method.getEvaluationMethod() == null || method.getEvaluationMethod().getId() == null) {
//                rs.rejectValue("outlineMethodSet[" + i + "].evaluationMethod.id", "error.outlineMethod.evaluationMethod", "Vui lòng chọn phương pháp đánh giá.");
//                hasErrors = true;
//            }
//        }
//        return hasErrors;
//    }

//    @RequestMapping("/sreachoutlines")
//    public String sreachOutlineDetail(Model model, @RequestParam Map<String, String> params){
//        
//        model.addAttribute("soutlines", this.outService.sreachOutlines(params));
//        
//        return "sreachoutlines";
//    }
//    public String sreachOutlineDetail(Model model,
//            @RequestParam(required = false) String nameCourse,
//            @RequestParam(required = false) Integer credit,
//            @RequestParam(required = false) String teacherName,
//            @RequestParam(required = false) String term) {
//
//        List<Outline> outlines = this.outService.sreachOutlines(nameCourse, credit, teacherName, term);
//        model.addAttribute("soutlines", outlines);
////        model.addAttribute("soutlines", this.outService.sreachOutlines(nameCourse, Integer.SIZE, teacherName, term));
//
//        return "sreachoutlines";
//    }
}
