package ru.babincev.trainingweb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.babincev.trainingweb.model.Appointment;
import ru.babincev.trainingweb.model.Trainer;
import ru.babincev.trainingweb.service.AppointmentService;
import ru.babincev.trainingweb.service.TrainerService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Controller
@RequestMapping("/trainers")
@RequiredArgsConstructor
public class TrainerController {

    private final TrainerService trainerService;
    private final AppointmentService appointmentService;

    @GetMapping()
    public String getAllTrainers(Model model){
        model.addAttribute("trainerList", trainerService.findAll());
        return "/trainers/trainers";
    }

    @PostMapping()
    public String imageUpload(@RequestParam MultipartFile img, HttpSession session, @ModelAttribute("trainer") Trainer trainer) {


        trainer.setImageName(img.getOriginalFilename());

        Trainer savedTrainer = trainerService.save(trainer);

        if (savedTrainer != null) {
            try {

                File saveFile = new ClassPathResource("static/img").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + img.getOriginalFilename());
                //System.out.println(path);
                Files.copy(img.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        session.setAttribute("msg", "Image Upload Sucessfully");

        return "redirect:/trainers";
    }

    @GetMapping("/{id}")
    public String getTrainerPage(@PathVariable("id") int id, Model model){
        Optional<Trainer> trainer = trainerService.findOne(id);
        if (trainer.isPresent()){
            model.addAttribute("trainer", trainer.get());
            model.addAttribute("appointmentList", trainerService.getAppointmentList(trainer.get()));
            return "trainers/trainer_profile";
        } else {
            return "redirect:/trainers";
        }
    }
    @GetMapping("/new")
    public String addTrainer(Model model){
        model.addAttribute("trainer", new Trainer());
        return "trainers/new";
    }

    @GetMapping("/{id}/edit")
    public String editTrainer(@PathVariable("id") int id, Model model){
        model.addAttribute("trainer", trainerService.findOne(id).get());
        return "trainers/edit";
    }

    @PatchMapping("/{id}")
    public String editTrainer(@PathVariable("id") int id, @ModelAttribute("trainer") Trainer trainer){
        trainerService.update(trainer, id);
        return "redirect:/trainers/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteTrainer(@PathVariable("id") int id){
        trainerService.delete(id);
        return "redirect:/trainers";
    }

    @GetMapping("/{id}/add-appointment")
    public String addAppointment(@PathVariable("id") int id, Model model){
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("id", id);
        return "trainers/add-appointment";
    }


    @PostMapping("/{id}/add-appointment")
    public String addAppointment(@PathVariable("id") int id, @ModelAttribute("appointment")Appointment appointment) {
        Trainer trainer = trainerService.findOne(id).get();
        appointmentService.add(appointment, trainer);
        return "redirect:/trainers/" + id;
    }

}
