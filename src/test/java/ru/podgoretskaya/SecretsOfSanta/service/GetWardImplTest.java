package ru.podgoretskaya.SecretsOfSanta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;
import ru.podgoretskaya.SecretsOfSanta.entity.QuestionnaireEntity;
import ru.podgoretskaya.SecretsOfSanta.mapper.QuestionnaireMapper;
import ru.podgoretskaya.SecretsOfSanta.repository.QuestionnaireRepo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class GetWardImplTest {
    ObjectMapper objectMapper = new ObjectMapper();
    QuestionnaireMapper questionnaireMapper = new QuestionnaireMapper();
    QuestionnaireRepo questionnaireRepo = Mockito.mock(QuestionnaireRepo.class);
    GetWard getWard = new GetWardImpl(questionnaireMapper, questionnaireRepo);

    @Test
    void test1() throws Exception {
        QuestionnaireDTO dto = objectMapper.readValue(new File("src/test/resources/test1.json"), QuestionnaireDTO.class);
        QuestionnaireDTO dto2 = objectMapper.readValue(new File("src/test/resources/test2.json"), QuestionnaireDTO.class);
        QuestionnaireEntity questionnaireEntity = questionnaireMapper.mapQuestionnaireToEntity(dto);
        List<QuestionnaireEntity> listQ = new ArrayList<>();
        listQ.add(questionnaireMapper.mapQuestionnaireToEntity(dto));
        listQ.add(questionnaireMapper.mapQuestionnaireToEntity(dto2));

        Mockito.when(questionnaireRepo.findAll()).thenReturn(listQ);
        Mockito.when(questionnaireRepo.findById(1L)).thenReturn(questionnaireEntity);
        Mockito.when(questionnaireRepo.save(Mockito.any())).thenReturn(questionnaireEntity);

        QuestionnaireDTO id = getWard.getWard(2L);
        assertEquals(questionnaireEntity.getApplicationID(), id);
    }
}