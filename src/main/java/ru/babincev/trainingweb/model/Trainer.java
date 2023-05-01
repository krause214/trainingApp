package ru.babincev.trainingweb.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="trainer")
public class Trainer {

    @Id
    @Column(name="trainer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "trainer")
    private List<Appointment> appointmentList;

    @NotNull
    @Column(name="name")
    private String name;

    @NotNull
    @Column(name="surname")
    private String surname;

    @NotNull
    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name="image_name")
    private String imageName;
}
