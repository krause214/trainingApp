package ru.babincev.trainingweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class TrainingWebController {

    @GetMapping()
    public String getMainPage(){
        return "main";
    }
    @GetMapping("/app")
    public String helloWorld(){
        return "hello";
    }
}
