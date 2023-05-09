package ru.babincev.trainingweb.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.babincev.trainingweb.model.Trainer;
import ru.babincev.trainingweb.service.TrainerService;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    @Transactional
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

    @GetMapping("/new")
    public String addTrainer(Model model){
        model.addAttribute("trainer", new Trainer());
        return "trainers/new";
    }

}
