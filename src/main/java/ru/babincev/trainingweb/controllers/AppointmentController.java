package ru.babincev.trainingweb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.babincev.trainingweb.model.Appointment;
import ru.babincev.trainingweb.service.AppointmentService;
import ru.babincev.trainingweb.service.TrainerService;

@Controller
@RequestMapping("/appointment")
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final TrainerService trainerService;

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("appointment", appointmentService.findOne(id).get());
        return "appointments/take";
    }

    @PatchMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, @ModelAttribute("appointment") Appointment appointment){
        appointmentService.update(appointment, id);
        return "redirect:/trainers";
    }

}
