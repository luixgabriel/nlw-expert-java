package br.com.luix.certification.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luix.certification.modules.students.dto.VerifyHasCertificationDTO;
import br.com.luix.certification.modules.students.useCases.VerifyIfHasCertificationUseCase;

@RestController
@RequestMapping("/students")
public class StudentController {

    //Preciso usar meu use-case
    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @PostMapping("")
    public String verifyIfHasCertification(@RequestBody VerifyHasCertificationDTO verifyHasCertificationDTO){
        System.out.println("oi");
        return "Usuário habilitado";
    }
    
}
