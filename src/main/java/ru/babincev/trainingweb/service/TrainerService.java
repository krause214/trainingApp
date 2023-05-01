package ru.babincev.trainingweb.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.babincev.trainingweb.model.Trainer;
import ru.babincev.trainingweb.repository.TrainerRepository;

import javax.persistence.Table;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;

    public Optional<Trainer> findOne(int id){
        return trainerRepository.findById(id);
    }

    public List<Trainer> findAll(){
        return trainerRepository.findAll();
    }

    //todo add trainer

    //todo delete trainer

    //todo update trainer
}
