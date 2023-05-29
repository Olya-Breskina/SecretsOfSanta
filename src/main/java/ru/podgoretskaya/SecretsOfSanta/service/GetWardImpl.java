package ru.podgoretskaya.SecretsOfSanta.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;
import ru.podgoretskaya.SecretsOfSanta.entity.QuestionnaireEntity;
import ru.podgoretskaya.SecretsOfSanta.mapper.QuestionnaireMapper;
import ru.podgoretskaya.SecretsOfSanta.repository.QuestionnaireRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetWardImpl implements GetWard {
    private final QuestionnaireMapper questionnaireMapper;
    private final QuestionnaireRepo questionnaireRepo;


    @Override
    public QuestionnaireDTO getWard(long id) {
        QuestionnaireEntity questionnaireEntity;
        List<QuestionnaireEntity> all = questionnaireRepo.findAll();
        Optional<QuestionnaireEntity> first = all.stream().filter(q ->q.getParticipantIsBusy()!=null
                && q.getParticipantIsBusy() == id).findFirst();
        if (first.isPresent()) {
            return questionnaireMapper.mapQuestionnaireToDto(first.get()) ;
        }

            log.info("\n>>> генерация подопечного для пользователя с id=" + id + ">>>\n");
            try {
                Long newID;
                Long participantIsBusy;
                do {

                    List<Long> allIds = all.stream().map(QuestionnaireEntity::getApplicationID).toList(); //questionnaireRepo.findAllIds();
                   // Collections.shuffle(allIds);//перемешали
                    int randomIndex = new Random().nextInt(allIds.size());
                    newID = allIds.get(randomIndex);
                    questionnaireEntity = questionnaireRepo.findById(newID).orElseThrow();
                    participantIsBusy = questionnaireEntity.getParticipantIsBusy();
                }
                while ((id == newID) && (participantIsBusy == null));

                questionnaireEntity = questionnaireRepo.findById(newID).orElseThrow();
                try {
                    questionnaireEntity.setParticipantIsBusy(id);
                    questionnaireRepo.save(questionnaireEntity);
                } catch (Exception e) {
                    log.error("\n>>>ошибка сохранения>>>\n");
                }

                log.info("\n>>>подопечным буддет пользователь с id=" + newID + ">>>\n");
                return questionnaireMapper.mapQuestionnaireToDto(questionnaireEntity);
            } catch (Exception e) {
                log.error("\n>>> подопечного нет>>>\n");
                throw e;
            }
        }
}
