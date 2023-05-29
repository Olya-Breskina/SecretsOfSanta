package ru.podgoretskaya.SecretsOfSanta.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;
import ru.podgoretskaya.SecretsOfSanta.entity.QuestionnaireEntity;
import ru.podgoretskaya.SecretsOfSanta.mapper.QuestionnaireMapper;
import ru.podgoretskaya.SecretsOfSanta.repository.QuestionnaireRepo;

@Service
@RequiredArgsConstructor
@Slf4j
public class FillingOutTheQuestionnaireImpl implements FillingOutTheQuestionnaire {
    private final QuestionnaireMapper questionnaireMapper;
    private final QuestionnaireRepo questionnaireRepo;

    @Override
    public Long saveQuestionnaireInDB(QuestionnaireDTO questionnaireDTO) {
        log.info("\n>>> сохраниение  анкеты " + questionnaireDTO.toString() + ">>>\n");
        try {
            QuestionnaireEntity saved = questionnaireRepo.save(questionnaireMapper.mapQuestionnaireToEntity(questionnaireDTO));
            return saved.getApplicationID();
        } catch (Exception e) {
            log.error("\n>>> хрень" + e.getMessage() + ">>>\n");
            throw e;
        }
    }
}
