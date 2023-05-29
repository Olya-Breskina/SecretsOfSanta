package ru.podgoretskaya.SecretsOfSanta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;
import ru.podgoretskaya.SecretsOfSanta.entity.QuestionnaireEntity;
import ru.podgoretskaya.SecretsOfSanta.mapper.QuestionnaireMapper;
import ru.podgoretskaya.SecretsOfSanta.repository.QuestionnaireRepo;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class FillingOutTheQuestionnaireImplTest {
    ObjectMapper objectMapper = new ObjectMapper();
    QuestionnaireMapper questionnaireMapper=new QuestionnaireMapper();
    QuestionnaireRepo questionnaireRepo= Mockito.mock(QuestionnaireRepo.class);
    FillingOutTheQuestionnaire fillingOutTheQuestionnaire=new FillingOutTheQuestionnaireImpl(questionnaireMapper,questionnaireRepo);

@Test
    void test1() throws Exception{
    QuestionnaireDTO dto=objectMapper.readValue(new File("src/test/resources/test1.json"), QuestionnaireDTO.class);
    QuestionnaireEntity questionnaireEntity=questionnaireMapper.mapQuestionnaireToEntity(dto);
    Mockito.when(questionnaireRepo.save(Mockito.any())).thenReturn(questionnaireEntity);

    Long id = fillingOutTheQuestionnaire.saveQuestionnaireInDB(dto);

    Mockito.verify(questionnaireRepo).save(questionnaireEntity);
    assertEquals(questionnaireEntity.getApplicationID(),id);
}
}