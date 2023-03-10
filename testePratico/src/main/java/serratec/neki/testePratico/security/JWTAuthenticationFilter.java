package serratec.neki.testePratico.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import serratec.neki.testePratico.model.Usuario;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // metodo principal, onde toda a requisição bate antes de chegar no endpoint
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // pega o token dentro da requisição
        String token = obterToken(request);
        System.out.println(token);
        // pega o id do usuário que está dentro do token
        Optional<Long> id = jwtService.obterIdUsuario(token);

        if (id.isPresent()) {

            // pega o usuario dono do token pelo id
            UserDetails usuario = customUserDetailsService.obterUsuarioPorId(id.get());

            CustomUsernamePasswordAuthenticationToken autenticacao = new CustomUsernamePasswordAuthenticationToken(usuario.getUsername(),null, (Usuario) usuario);

            // autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(autenticacao);

        }

        filterChain.doFilter(request, response);

    }

    private String obterToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        // verifca se veio espaco em branco dentro do token
        if (!StringUtils.hasText(token)) {
            return null;

        }

        return token.substring(7);
    }

}