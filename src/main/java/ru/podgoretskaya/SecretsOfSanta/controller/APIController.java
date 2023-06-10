package ru.podgoretskaya.SecretsOfSanta.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.podgoretskaya.SecretsOfSanta.dto.QuestionnaireDTO;
import ru.podgoretskaya.SecretsOfSanta.repository.QuestionnaireRepo;
import ru.podgoretskaya.SecretsOfSanta.service.FillingOutTheQuestionnaire;
import ru.podgoretskaya.SecretsOfSanta.service.GetWard;

import java.util.List;

@Controller
@Slf4j
@SuppressWarnings("unused")
@Tag(name="Тайный Санта", description = "Методы для реализации игры")
@RequestMapping("/SecretsOfSanta")
@RequiredArgsConstructor
public class APIController {
    private final FillingOutTheQuestionnaire fillingOutTheQuestionnaire;
    private final GetWard getWard;

    @PostMapping("/in")
    @Operation(summary = "заполнение анкеты для участия в игре")
    public ResponseEntity<Long> saveQuestionnaire(@RequestBody QuestionnaireDTO model) {
        Long id = fillingOutTheQuestionnaire.saveQuestionnaireInDB(model);
        return ResponseEntity.ok(id);
    }
    @PostMapping("/out/{id}")
    @Operation(summary = "геренация одаряемого")
    public ResponseEntity<QuestionnaireDTO> giftRecipient(@PathVariable long id){
        return new  ResponseEntity<>(getWard.getWard(id), HttpStatus.OK);
    }
}
