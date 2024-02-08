package br.com.luix.certification.modules.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luix.certification.modules.questions.dto.AlternativesResultDTO;
import br.com.luix.certification.modules.questions.dto.QuestionResultDTO;
import br.com.luix.certification.modules.questions.entities.AlternativesEntity;
import br.com.luix.certification.modules.questions.entities.QuestionEntity;
import br.com.luix.certification.modules.questions.repositories.QuestionRepository;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;
    
    @GetMapping("/technology/{tech}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String tech){
        var result = this.questionRepository.findByTechnology(tech);
        var toMap = result.stream().map(question -> mapQuestionToDto(question)).collect(Collectors.toList());
        return toMap;

    }

    static QuestionResultDTO mapQuestionToDto(QuestionEntity question){
        // Usando o builder do lombok eu já instacio a classe
        var questionResultDTO = QuestionResultDTO.builder().description(question.getDescription()).id(question.getId()).technology(question.getTechnology()).build();

        List<AlternativesResultDTO> alternativesResultDTOs = question.getAlternatives().stream().map((alternatives) -> mapAlternativeToDto(alternatives)).collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;

    }

    static AlternativesResultDTO mapAlternativeToDto(AlternativesEntity alternativesResultDTO){
        return AlternativesResultDTO.builder()
        .id(alternativesResultDTO.getId())
        .description(alternativesResultDTO.getDescription()).build();
    }
}
