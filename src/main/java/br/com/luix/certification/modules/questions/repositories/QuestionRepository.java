package br.com.luix.certification.modules.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.luix.certification.modules.questions.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {

    // Posso achar pelo nome de algum parametro da entity sem precisar fazer uma query
    List<QuestionEntity> findByTechnology(String technology);
    
}
