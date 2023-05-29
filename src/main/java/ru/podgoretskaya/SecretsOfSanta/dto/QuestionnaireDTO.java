package ru.podgoretskaya.SecretsOfSanta.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "анкета")
public class QuestionnaireDTO {
    @Schema(description = "имя")
    private String firstName;
    @Schema(description = "фамилия")
    private String lastName;
    @Schema(description = "таб.номер")
    private String serviceNumber;
    @Schema(description = "хобби")
    private String hobby;
    @Schema(description = "идеи подарка")
    private String giftIdeas;
}
