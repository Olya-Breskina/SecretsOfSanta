package ru.podgoretskaya.SecretsOfSanta.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "Questionnaire")
public class QuestionnaireEntity {
    @Id
    @SequenceGenerator(name = "SecretsOfSantaGenerator", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SecretsOfSantaGenerator")
    @Column(name = "id", nullable = false)

    private Long applicationID;

    @Column(name ="first_name")
    @NotNull
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Column(name = "service_number")
    @NotNull
    private String serviceNumber;

    @Column(name ="hobby")
    private String hobby;

    @Column(name ="gift_ideas")
    private String giftIdeas;

    @Column(name ="participant_is_busy")
    private Long participantIsBusy;// id того, кто дарит подарок
}
