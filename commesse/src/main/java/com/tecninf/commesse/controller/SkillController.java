//package com.tecninf.commesse.controller;
//
//import com.tecninf.commesse.entity.Skill;
//import com.tecninf.commesse.service.SkillService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("api/commesse")
//public class SkillController {
//
//    @Autowired
//    private SkillService skillService;
//
//
//    @GetMapping("/skills")
//    @PreAuthorize("hasRole('ADMIN')")
//    public List<Skill> getAllSkills() {
//        return skillService.getSkills();
//    }
//
//}
