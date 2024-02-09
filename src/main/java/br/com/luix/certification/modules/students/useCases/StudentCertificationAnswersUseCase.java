package br.com.luix.certification.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.luix.certification.modules.questions.entities.QuestionEntity;
import br.com.luix.certification.modules.questions.repositories.QuestionRepository;
import br.com.luix.certification.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.luix.certification.modules.students.entities.AnswersCertificationsEntity;
import br.com.luix.certification.modules.students.entities.CertificationStudentEntity;
import br.com.luix.certification.modules.students.entities.StudentEntity;
import br.com.luix.certification.modules.students.repositories.CertificationStudentRepository;
import br.com.luix.certification.modules.students.repositories.StudentRepository;

public class StudentCertificationAnswersUseCase {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    // Quem chamar esse metódo vai precisar tratar o erroS
     public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto){
        // var student = studentRepository.findByEmail(dto.getEmail());

        // if(student.isEmpty()){
        //     throw new Exception("Usuário não encontrado!");
        // }

             // Buscar as alternativas das perguntas
            // - Correct ou Incorreta
        List<QuestionEntity> questionsEntity = this.questionRepository.findByTechnology(dto.getTechnology());

        dto.getQuestionAnswers().stream().forEach(questionAnswer -> {
            var questionSelected = questionsEntity.stream().filter(question -> question.getId().equals(questionAnswer.getQuestionID())).findFirst().get();

            var findCorrectAlternative = questionSelected.getAlternatives().stream().filter(alternative -> alternative.isCorrect()).findFirst().get();

            if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())){
                questionAnswer.setCorrect(true);
            }else {
                questionAnswer.setCorrect(false);
            }

            var answersCertificationsEntity = AnswersCertificationsEntity.builder()
            .answerID(questionAnswer.getAlternativeID())
            .questionID(questionAnswer.getQuestionID())
            .isCorrect(questionAnswer.isCorrect()).build();
        });

        

        // Verificar se existe estudante pelo email

        var studentExists = this.studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if(studentExists.isEmpty()){
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentID = studentCreated.getId();
        }else{
            studentID = studentExists.get().getId();
        }

        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<>();

        CertificationStudentEntity certificationStudentEntity = CertificationStudentEntity.builder()
        .technology(dto.getTechnology())
        .studentID(studentID)
        .answersCertificationsEntity(answersCertifications)
        .build();

        var certificationStudentCreated = certificationStudentRepository.save(certificationStudentEntity);

        return certificationStudentCreated;

        // return dto;
    }
}
