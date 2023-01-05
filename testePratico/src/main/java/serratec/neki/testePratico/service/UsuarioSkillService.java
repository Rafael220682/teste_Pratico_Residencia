package serratec.neki.testePratico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serratec.neki.testePratico.Repository.UsuarioSkillRepository;
import serratec.neki.testePratico.exception.ResourceNotFoundException;
import serratec.neki.testePratico.model.UsuarioSkill;
import serratec.neki.testePratico.shared.dto.UsuarioSkillDto;

@Service
public class UsuarioSkillService {
    
    @Autowired
    private UsuarioSkillRepository usuarioSkillRepository;

    public List<UsuarioSkillDto> obterTodos(){
        List<UsuarioSkill> usuarioSkills = usuarioSkillRepository.findAll();

        return usuarioSkills.stream()
                .map(usuarioSkill -> new ModelMapper().map(usuarioSkill, UsuarioSkillDto.class))
                .collect(Collectors.toList());
    }


    public Optional<UsuarioSkillDto> obterPorId(Long id){
        
        Optional<UsuarioSkill> usuarioSkill = usuarioSkillRepository.findById(id);

        if(usuarioSkill.isEmpty()){
            throw new ResourceNotFoundException("Usuário com Id: " + id + " não encontrado.");
        }
        UsuarioSkillDto dto = new ModelMapper().map(usuarioSkill.get(), UsuarioSkillDto.class);
        return Optional.of(dto);
    }


    public UsuarioSkillDto adicionar(UsuarioSkillDto usuarioSkillDto){
        usuarioSkillDto.setId(null);

        ModelMapper mapper = new ModelMapper();

        UsuarioSkill usuarioSkill = mapper.map(usuarioSkillDto, UsuarioSkill.class);
        usuarioSkill = usuarioSkillRepository.save(usuarioSkill);
        usuarioSkillDto.setId(usuarioSkill.getId());

        return usuarioSkillDto;
    }


    public UsuarioSkillDto atualizar(Long id, UsuarioSkillDto usuarioSkillDto){
        usuarioSkillDto.setId(id);

        ModelMapper mapper = new ModelMapper();
        UsuarioSkill usuarioSkill = mapper.map(usuarioSkillDto, UsuarioSkill.class);
        usuarioSkillRepository.save(usuarioSkill);
        
        return usuarioSkillDto;
    }


    public void deletar(long id){
        Optional<UsuarioSkill> usuarioSkill = usuarioSkillRepository.findById(id);
        if(usuarioSkill.isEmpty()){
            throw new ResourceNotFoundException("Usuário com Id: " + id + " não encontrado.");
        }
        usuarioSkillRepository.deleteById(id);

    }
}
