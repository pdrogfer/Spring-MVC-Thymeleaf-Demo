package com.pgf.thymeleaf_demo.controller;

import com.pgf.thymeleaf_demo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    // this annotation injects a value from application.properties
    @Value("${countries}")
    private List<String> countries;
    @Value("${languages}")
    private List<String> languages;
    @Value("${operatingSystems}")
    private List<String> oSystems;

    @GetMapping("/showStudentForm")
    public String showForm(Model theModel) {

        Student theStudent = new Student();
        theModel.addAttribute("student", theStudent);
        theModel.addAttribute("countries", countries);
        theModel.addAttribute("languages", languages);
        theModel.addAttribute("favoriteOperatingSystems", oSystems);

        return "student-form";
    }

    @PostMapping("/processStudentForm")
    public String processForm(
            @ModelAttribute("student") Student theStudent
    ) {

        System.out.println("the student: " + theStudent.toString());
        return "student-confirmation";
    }
}
