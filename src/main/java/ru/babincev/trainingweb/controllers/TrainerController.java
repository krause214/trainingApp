package ru.babincev.trainingweb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.babincev.trainingweb.service.TrainerService;

@Controller
@RequestMapping("/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;

    @GetMapping()
    public String getAllTrainers(Model model){
        model.addAttribute("trainerList", trainerService.findAll());
        return "/trainers/trainers";
    }


}
