package serratec.neki.testePratico.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import serratec.neki.testePratico.model.UsuarioSkill;

public interface UsuarioSkillRepository extends JpaRepository<UsuarioSkill, Long> {
    
}
