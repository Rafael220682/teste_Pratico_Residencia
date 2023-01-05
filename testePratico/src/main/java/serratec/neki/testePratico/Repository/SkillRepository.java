package serratec.neki.testePratico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import serratec.neki.testePratico.model.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    
}
