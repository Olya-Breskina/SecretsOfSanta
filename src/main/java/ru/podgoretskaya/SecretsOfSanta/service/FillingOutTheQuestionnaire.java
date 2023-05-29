package ru.podgoretskaya.SecretsOfSanta.service;

import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;

public interface FillingOutTheQuestionnaire {
    Long  saveQuestionnaireInDB(QuestionnaireDTO questionnaireDTO);
}
