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

import serratec.neki.testePratico.Repository.UsuarioSkillRepository;
import serratec.neki.testePratico.service.UsuarioSkillService;
import serratec.neki.testePratico.shared.dto.UsuarioSkillDto;
import serratec.neki.testePratico.view.model.UsuarioSkillRequest;
import serratec.neki.testePratico.view.model.UsuarioSkillResponse;

@RestController
@RequestMapping("/usuarioSkill")
public class UsuarioSkillController {

    @Autowired
    private UsuarioSkillService usuarioSkillService;

    @GetMapping
    public ResponseEntity<List<UsuarioSkillResponse>> obterTodos(){
        List<UsuarioSkillDto> usuarioSkill = usuarioSkillService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<UsuarioSkillResponse> resposta = usuarioSkill.stream()
            .map(usuarioSkillDto -> mapper.map(usuarioSkillDto, UsuarioSkillResponse.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioSkillResponse>> obterPorId(@PathVariable Long id){
        Optional<UsuarioSkillDto> dto = usuarioSkillService.obterPorId(id);
        UsuarioSkillResponse usuarioSkill = new ModelMapper().map(dto.get(), UsuarioSkillResponse.class);

        return new ResponseEntity<>(Optional.of(usuarioSkill), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioSkillResponse> adicionar(@RequestBody UsuarioSkillRequest usuarioSkillReq){
       ModelMapper mapper = new ModelMapper();

       UsuarioSkillDto dto = mapper.map(usuarioSkillReq, UsuarioSkillDto.class);
             
       dto = usuarioSkillService.adicionar(dto);

       return new ResponseEntity<>(mapper.map(dto, UsuarioSkillResponse.class), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioSkillResponse> atualizar(@RequestBody UsuarioSkillRequest usuarioSkillReq, @PathVariable Long id){
       
        ModelMapper mapper = new ModelMapper();
        UsuarioSkillDto usuarioSkillDto = mapper.map(usuarioSkillReq, UsuarioSkillDto.class);

        usuarioSkillDto = usuarioSkillService.atualizar(id, usuarioSkillDto);

        return new ResponseEntity<> (
            mapper.map(usuarioSkillDto, UsuarioSkillResponse.class),
            HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        usuarioSkillService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    
}
