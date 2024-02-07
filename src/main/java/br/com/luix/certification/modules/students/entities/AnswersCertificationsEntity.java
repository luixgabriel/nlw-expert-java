package br.com.luix.certification.modules.students.entities;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswersCertificationsEntity {
    private UUID id;
    private UUID certificationsID;
    private UUID studentID;
    private UUID questionID;
    private UUID answerID;
    private boolean isCorrect;
}
