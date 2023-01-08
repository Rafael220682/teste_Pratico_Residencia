package serratec.neki.testePratico.security;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import serratec.neki.testePratico.model.Usuario;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken implements UserDetails{
	private static final long serialVersionUID = 1L;

	private Usuario usuario;

	public CustomUsernamePasswordAuthenticationToken(String username, String password, Usuario usuario) {
		super(username, password, Arrays.asList(new SimpleGrantedAuthority("USER")));
		this.usuario = usuario;
		
		
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	

	
	
	

}
