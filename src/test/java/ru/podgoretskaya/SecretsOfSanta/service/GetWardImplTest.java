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
import static org.junit.jupiter.api.Assertions.assertNotNull;


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
        QuestionnaireEntity questionnaireEntity1 = questionnaireMapper.mapQuestionnaireToEntity(dto);
        questionnaireEntity1.setApplicationID(1L);
        listQ.add( questionnaireEntity1);
        QuestionnaireEntity questionnaireEntity2 = questionnaireMapper.mapQuestionnaireToEntity(dto2);
        questionnaireEntity2.setApplicationID(2L);
        listQ.add(questionnaireEntity2);



        Mockito.when(questionnaireRepo.findAll()).thenReturn(listQ);
        Mockito.when(questionnaireRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(questionnaireEntity));
        Mockito.when(questionnaireRepo.save(Mockito.any())).thenReturn(questionnaireEntity);

        QuestionnaireDTO actualidRespons = getWard.getWard(1L);
        assertNotNull(actualidRespons);
    }
}