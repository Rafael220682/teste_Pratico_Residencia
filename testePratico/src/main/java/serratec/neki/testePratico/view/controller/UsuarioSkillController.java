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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import serratec.neki.testePratico.exception.ResourceBadRequestException;
import serratec.neki.testePratico.model.UsuarioSkill;
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
    @ApiOperation(value = "Lista todos as relações Usuários Skills", notes = "Lista todas as relações Usuários Skills")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista Usuários_Skills"),
        @ApiResponse(code = 401, message = "Erro de autenticação"),
        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
        @ApiResponse(code = 404, message = "Recurso não encontrado"),
        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
    })
    public ResponseEntity<List<UsuarioSkillResponse>> obterTodos(){
        List<UsuarioSkillDto> usuarioSkill = usuarioSkillService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<UsuarioSkillResponse> resposta = usuarioSkill.stream()
            .map(usuarioSkillDto -> mapper.map(usuarioSkillDto, UsuarioSkillResponse.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lista usuário_Skill por Id", notes = "Lista usuário_Skill")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista usuário_Skill"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<Optional<UsuarioSkillResponse>> obterPorId(@PathVariable Long id){
        Optional<UsuarioSkillDto> dto = usuarioSkillService.obterPorId(id);
        UsuarioSkillResponse usuarioSkill = new ModelMapper().map(dto.get(), UsuarioSkillResponse.class);

        return new ResponseEntity<>(Optional.of(usuarioSkill), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra usuário_Skill", notes = "Cadastra usuário_Skill")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cadastra usuário_Skill"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<UsuarioSkillResponse> adicionar(@RequestBody UsuarioSkillRequest usuarioSkillReq){
       ModelMapper mapper = new ModelMapper();
       mapper.getConfiguration().setAmbiguityIgnored(true);

       UsuarioSkillDto dto = mapper.map(usuarioSkillReq, UsuarioSkillDto.class);
             
       dto = usuarioSkillService.adicionar(dto);

       return new ResponseEntity<>(mapper.map(dto, UsuarioSkillResponse.class), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Altera dados do usuário_Skill", notes = "Altera dados do usuário_Skill")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Altera usuário_Skill"),
        @ApiResponse(code = 401, message = "Erro de autenticação"),
        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
        @ApiResponse(code = 404, message = "Recurso não encontrado"),
        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
    })
    public ResponseEntity<UsuarioSkillResponse> atualizar(@RequestBody UsuarioSkillRequest usuarioSkillReq, @PathVariable Long id){
       
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        
        UsuarioSkillDto usuarioSkillDto = mapper.map(usuarioSkillReq, UsuarioSkillDto.class);

        usuarioSkillDto = usuarioSkillService.atualizar(id, usuarioSkillDto);

        return new ResponseEntity<> (
            mapper.map(usuarioSkillDto, UsuarioSkillResponse.class),
            HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta usuário_Skill", notes = "Deleta usuário_Skill")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Deleta usuário_Skill"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<?> deletar(@PathVariable Long id){
        usuarioSkillService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
