package ru.babincev.trainingweb.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="appointment")
public class Appointment {

    @Id
    @Column(name="appointment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int appointment_id;

    @ManyToOne
    @JoinColumn(name="trainer_id", referencedColumnName = "trainer_id")
    private Trainer trainer;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name = "description")
    private String description;

    public boolean hasPerson(){
        return (!name.isEmpty() || !surname.isEmpty());
    }
}