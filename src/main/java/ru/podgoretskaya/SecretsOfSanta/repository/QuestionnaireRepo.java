package ru.podgoretskaya.SecretsOfSanta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.podgoretskaya.SecretsOfSanta.entity.QuestionnaireEntity;

import java.util.List;


public interface QuestionnaireRepo extends JpaRepository<QuestionnaireEntity, Long> {
    @Query(value = "SELECT q.id FROM questionnaire q",nativeQuery = true)
    List<Long> findAllIds();// в каком виде олучить отвыт название метода (логика метода  выше)

    @Query(value = "SELECT q.participant_is_busy FROM questionnaire q",nativeQuery = true)
    List<Long> findAllParticipantIsBusy();// в каком виде получить ответ название метода (логика метода  выше)
}