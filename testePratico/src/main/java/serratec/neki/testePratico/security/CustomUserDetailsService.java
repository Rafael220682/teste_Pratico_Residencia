package serratec.neki.testePratico.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import serratec.neki.testePratico.service.UsuarioService;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UsuarioService usuarioService;

    public UserDetails loadUserByUsername (String login) {
        return usuarioService.obterPorLogin(login).get();
    
    }

    public UserDetails obterUsuarioPorId (Long id) {
        return usuarioService.obterUsuarioPorId(id);
    }

}
