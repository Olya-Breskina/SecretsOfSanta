package ru.podgoretskaya.SecretsOfSanta.mapper;

import org.springframework.stereotype.Component;
import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;
import ru.podgoretskaya.SecretsOfSanta.entity.QuestionnaireEntity;

@Component
public class QuestionnaireMapper {
    public QuestionnaireDTO mapQuestionnaireToDto (QuestionnaireEntity entity){
        QuestionnaireDTO dto=new QuestionnaireDTO();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setServiceNumber(entity.getServiceNumber());
        dto.setHobby(entity.getHobby());
        dto.setGiftIdeas(entity.getGiftIdeas());
        return dto;
    }
    public QuestionnaireEntity mapQuestionnaireToEntity(QuestionnaireDTO dto){
        QuestionnaireEntity entity=new QuestionnaireEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setServiceNumber(dto.getServiceNumber());
        entity.setHobby(dto.getHobby());
        entity.setGiftIdeas(dto.getGiftIdeas());
        return entity;
    }
}
