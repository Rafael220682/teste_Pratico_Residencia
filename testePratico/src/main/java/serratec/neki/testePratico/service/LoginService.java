package serratec.neki.testePratico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import serratec.neki.testePratico.Repository.UsuarioRepository;
import serratec.neki.testePratico.model.Usuario;
import serratec.neki.testePratico.security.JWTService;
import serratec.neki.testePratico.view.model.LoginResponse;

@Service
public class LoginService {
    
    @Autowired
    private UsuarioRepository usuarioRepositorio;

    @Autowired
	private JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;



public LoginResponse logar(String login, String password){

    Authentication autenticacao = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(login, password));

    SecurityContextHolder.getContext().setAuthentication(autenticacao);

    String token = jwtService.gerarToken(autenticacao);

    Usuario usuario = usuarioRepositorio.findByLogin(login).get();

    return new LoginResponse(token, usuario);

}
}
