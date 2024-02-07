package br.com.luix.certification.modules.students.useCases;

import org.springframework.stereotype.Service;

import br.com.luix.certification.modules.students.dto.VerifyHasCertificationDTO;

@Service
public class VerifyIfHasCertificationUseCase {

    public boolean execute(VerifyHasCertificationDTO dto){
        if(dto.getEmail().equals("luis@gmail.com")){
            return true;
        }
           return false; 
    }
    
}
