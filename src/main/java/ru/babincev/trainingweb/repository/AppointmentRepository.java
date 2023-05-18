package ru.babincev.trainingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.babincev.trainingweb.model.Appointment;
import ru.babincev.trainingweb.model.Trainer;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    public List<Appointment> findAllByTrainer(Trainer trainer);
}
