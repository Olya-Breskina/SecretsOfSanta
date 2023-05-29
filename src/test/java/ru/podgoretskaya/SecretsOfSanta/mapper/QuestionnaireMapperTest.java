package ru.podgoretskaya.SecretsOfSanta.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;
import ru.podgoretskaya.SecretsOfSanta.entity.QuestionnaireEntity;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class QuestionnaireMapperTest {
    ObjectMapper objectMapper = new ObjectMapper();
    QuestionnaireMapper questionnaireMapper = new QuestionnaireMapper();

    @Test
    void MapQuestionnaireToDtoTest() throws Exception {
        QuestionnaireEntity qEntity = objectMapper.readValue(new File("src/test/resources/test1.json"), QuestionnaireEntity.class);

        QuestionnaireDTO dto = questionnaireMapper.mapQuestionnaireToDto(qEntity);

        assertEquals(qEntity.getFirstName(), dto.getFirstName());
        assertEquals(qEntity.getGiftIdeas(), dto.getGiftIdeas());
    }

    @Test
    void mapQuestionnaireToEntityTest() throws Exception {
        QuestionnaireDTO dto = objectMapper.readValue(new File("src/test/resources/test1.json"), QuestionnaireDTO.class);
        QuestionnaireEntity questionnaireEntity = questionnaireMapper.mapQuestionnaireToEntity(dto);

        assertEquals(questionnaireEntity.getLastName(), dto.getLastName());
        assertEquals(questionnaireEntity.getHobby(), dto.getHobby());
        assertEquals(questionnaireEntity.getServiceNumber(), dto.getServiceNumber());
    }
}