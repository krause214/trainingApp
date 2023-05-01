package ru.babincev.trainingweb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.babincev.trainingweb.model.Appointment;
import ru.babincev.trainingweb.repository.AppointmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    private Optional<Appointment> findOne(int id){
        return appointmentRepository.findById(id);
    }

    private List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    //todo add Appointment

    //todo delete Appointment

    //todo update Appointment

}
