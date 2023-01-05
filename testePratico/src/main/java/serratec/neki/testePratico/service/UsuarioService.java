package serratec.neki.testePratico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serratec.neki.testePratico.Repository.UsuarioRepository;
import serratec.neki.testePratico.exception.ResourceNotFoundException;
import serratec.neki.testePratico.model.Usuario;
import serratec.neki.testePratico.shared.dto.UsuarioDto;


@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;


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


    public UsuarioDto adicionar(UsuarioDto usuarioDto){
        usuarioDto.setId(null);

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
