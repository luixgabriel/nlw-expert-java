package br.com.luix.certification.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.luix.certification.modules.students.dto.VerifyHasCertificationDTO;
import br.com.luix.certification.modules.students.repositories.CertificationStudentRepository;

@Service
public class VerifyIfHasCertificationUseCase {

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    public boolean execute(VerifyHasCertificationDTO dto){
        System.out.println(dto);
        var result = this.certificationStudentRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        if(result.isEmpty()){
            return true;
        }
           return false; 
    }
    
}
