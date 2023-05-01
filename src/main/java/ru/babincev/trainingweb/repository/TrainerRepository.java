package ru.babincev.trainingweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.babincev.trainingweb.model.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
}
