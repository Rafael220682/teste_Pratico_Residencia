package serratec.neki.testePratico.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import serratec.neki.testePratico.service.SkillService;
import serratec.neki.testePratico.shared.dto.SkillDto;
import serratec.neki.testePratico.view.model.SkillRequest;
import serratec.neki.testePratico.view.model.SkillResponse;


@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping
    public ResponseEntity<List<SkillResponse>> obterTodos(){
        List<SkillDto> skill = skillService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<SkillResponse> resposta = skill.stream()
            .map(skillDto -> mapper.map(skillDto, SkillResponse.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SkillResponse>> obterPorId(@PathVariable Long id){
        Optional<SkillDto> dto = skillService.obterPorId(id);
        SkillResponse skill = new ModelMapper().map(dto.get(), SkillResponse.class);

        return new ResponseEntity<>(Optional.of(skill), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SkillResponse> adicionar(@RequestBody SkillRequest skillReq){
       ModelMapper mapper = new ModelMapper();

       SkillDto dto = mapper.map(skillReq, SkillDto.class);
             
       dto = skillService.adicionar(dto);

       return new ResponseEntity<>(mapper.map(dto, SkillResponse.class), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SkillResponse> atualizar(@RequestBody SkillRequest skillReq, @PathVariable Long id){
       
        ModelMapper mapper = new ModelMapper();
        SkillDto skillDto = mapper.map(skillReq, SkillDto.class);

        skillDto = skillService.atualizar(id, skillDto);

        return new ResponseEntity<> (
            mapper.map(skillDto, SkillResponse.class),
            HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        skillService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

    


