package ru.babincev.trainingweb.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.babincev.trainingweb.model.Appointment;
import ru.babincev.trainingweb.model.Trainer;
import ru.babincev.trainingweb.repository.AppointmentRepository;
import ru.babincev.trainingweb.repository.TrainerRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TrainerService {

    private final TrainerRepository trainerRepository;
    private final AppointmentRepository appointmentRepository;

    public Optional<Trainer> findOne(int id){
        return trainerRepository.findById(id);
    }

    public List<Trainer> findAll(){
        return trainerRepository.findAll();
    }

    @Transactional
    public Trainer save(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    @Transactional
    public List<Appointment> getAppointmentList(Trainer trainer){
        return trainer.getAppointmentList();
    }

    @Transactional
    public void update(Trainer trainer, int id) {
        Trainer old = trainerRepository.findById(id).get();
        trainer.setId(id);
        trainer.setImageName(old.getImageName());
        trainerRepository.save(trainer);
    }

    @Transactional
    public void delete(int id) {
        Trainer trainer = trainerRepository.findById(id).get();
        List<Appointment> appointmentList = appointmentRepository.findAllByTrainer(trainer);
        appointmentRepository.deleteAll(appointmentList);
        trainerRepository.deleteById(id);
    }

}
