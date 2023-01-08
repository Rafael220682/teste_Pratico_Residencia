package serratec.neki.testePratico.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import serratec.neki.testePratico.Repository.UsuarioRepository;
import serratec.neki.testePratico.exception.ResourceNotFoundException;
import serratec.neki.testePratico.model.Usuario;
import serratec.neki.testePratico.shared.dto.UsuarioDto;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
	private PasswordEncoder passwordEncoder;

   
    public List<UsuarioDto> obterTodos(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(usuario -> new ModelMapper().map(usuario, UsuarioDto.class))
                .collect(Collectors.toList());
    }


    public Optional<UsuarioDto> obterPorId(Long id){
        
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if(usuario.isEmpty()){
            throw new ResourceNotFoundException("Usuário com Id: " + id + " não encontrado.");
        }
        UsuarioDto dto = new ModelMapper().map(usuario.get(), UsuarioDto.class);
        return Optional.of(dto);
    }

    public Usuario obterUsuarioPorId(Long id) {
		Optional<Usuario> optUsuario = usuarioRepository.findById(id);

		if (optUsuario.isEmpty()) {
			throw new ResourceNotFoundException("Não foi possivel encontrar a pessoa com id " + id);
		}
		return optUsuario.get();
	}

    public Optional<Usuario> obterPorLogin(String login){
        return usuarioRepository.findByLogin(login);
}

    public UsuarioDto adicionar(UsuarioDto usuarioDto){
        usuarioDto.setId(null);

        if(obterPorLogin(usuarioDto.getLogin()).isPresent()){
			//exceptions informando que o usuário existe
			throw new InputMismatchException("Já existe um usuário com este nome: " + usuarioDto.getLogin());
		}
		//codificando a senha para não ficar público, gerando um hash
		String senha = passwordEncoder.encode(usuarioDto.getPassword());
        usuarioDto.setPassword(senha);
       
        ModelMapper mapper = new ModelMapper();
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);

        usuario = usuarioRepository.save(usuario);

        usuarioDto.setId(usuario.getId());
        
	
        return usuarioDto;
    }


    public UsuarioDto atualizar(Long id, UsuarioDto usuarioDto){
        usuarioDto.setId(id);

        ModelMapper mapper = new ModelMapper();
        Usuario usuario = mapper.map(usuarioDto, Usuario.class);
        usuarioRepository.save(usuario);
        
        return usuarioDto;
    }


    public void deletar(long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isEmpty()){
            throw new ResourceNotFoundException("Usuário com Id: " + id + " não encontrado.");
        }
        usuarioRepository.deleteById(id);

    }

}
