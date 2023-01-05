package serratec.neki.testePratico.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import serratec.neki.testePratico.Repository.SkillRepository;
import serratec.neki.testePratico.exception.ResourceNotFoundException;
import serratec.neki.testePratico.model.Skill;
import serratec.neki.testePratico.shared.dto.SkillDto;


@Service
public class SkillService {

    @Value("${app.caminhoImagens}")
    private String caminhoImagens;


    @Autowired
    private SkillRepository skillRepository;

    public List<SkillDto> obterTodos(){
        List<Skill> skills = skillRepository.findAll();

        return skills.stream()
                .map(skill -> new ModelMapper().map(skill, SkillDto.class))
                .collect(Collectors.toList());
    }


    public Optional<SkillDto> obterPorId(Long id){
        
        Optional<Skill> skill =skillRepository.findById(id);

        if(skill.isEmpty()){
            throw new ResourceNotFoundException("Usuário com Id: " + id + " não encontrado.");
        }
        SkillDto dto = new ModelMapper().map(skill.get(), SkillDto.class);
        return Optional.of(dto);
    }


    public SkillDto adicionar(SkillDto skillDto, MultipartFile file){
        skillDto.setId(null);

        ModelMapper mapper = new ModelMapper();

        Skill skill = mapper.map(skillDto, Skill.class);
        skill = skillRepository.saveAndFlush(skill);
        skill = skillRepository.findById(skill.getId()).get();

        skillDto.setId(skill.getId());

        return skillDto;
    }


    public SkillDto atualizar(Long id, SkillDto skillDto){
        skillDto.setId(id);

        ModelMapper mapper = new ModelMapper();
        Skill skill = mapper.map(skillDto, Skill.class);
        skillRepository.save(skill);
        
        return skillDto;
    }


    public void deletar(long id){
        Optional<Skill> skill = skillRepository.findById(id);
        if(skill.isEmpty()){
            throw new ResourceNotFoundException("Usuário com Id: " + id + " não encontrado.");
        }
        skillRepository.deleteById(id);

    }

}
    

