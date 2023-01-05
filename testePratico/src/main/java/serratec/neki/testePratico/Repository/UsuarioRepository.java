package serratec.neki.testePratico.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import serratec.neki.testePratico.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByLogin(String login);
   
}
