package ru.babincev.trainingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.babincev.trainingweb.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
