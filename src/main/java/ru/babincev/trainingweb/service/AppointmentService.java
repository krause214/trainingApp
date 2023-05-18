package ru.babincev.trainingweb.service;

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
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public Optional<Appointment> findOne(int id){
        return appointmentRepository.findById(id);
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    @Transactional
    public void add(Appointment appointment, Trainer trainer) {
        appointment.setTrainer(trainer);
        appointment.setName("");
        appointment.setSurname("");
        appointmentRepository.save(appointment);
    }
    @Transactional
    public void update(Appointment appointment, int id) {
        Appointment old = appointmentRepository.findById(id).get();
        appointment.setDescription(old.getDescription());
        appointment.setAppointment_id(id);
        appointment.setTrainer(old.getTrainer());
        appointmentRepository.save(appointment);
    }

}
