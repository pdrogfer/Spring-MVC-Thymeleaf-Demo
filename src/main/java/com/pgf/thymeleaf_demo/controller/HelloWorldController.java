package com.pgf.thymeleaf_demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // controller method to show the initial HTML form
    @GetMapping("/showForm")
    public String showForm() {
        return "helloworld-form";
    }

    // controller method to process the HTML form
    @GetMapping("/processForm")
    public String processForm(
            @RequestParam("studentName") String studentName,
            Model theModel
    ) {

        theModel.addAttribute("studentNameFromController", studentName);

        // it will search for a thymeleaf template with "helloworld" name
        return "helloworld";
    }

    // alternative version, to receive all kinds of requests: GET, PUT...
    @RequestMapping("/processFormToUppercase")
    public String processFormToUppercase(
            HttpServletRequest request,
            Model theModel
    ) {

        String nameUppercased = nameToUpperCase(request.getParameter("studentName"));

        theModel.addAttribute("studentNameFromController", nameUppercased);

        // it will search for a thymeleaf template with "helloworld" name
        return "helloworld";
    }

    private String nameToUpperCase(String name) {
        return name.toUpperCase();
    }
}
