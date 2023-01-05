package serratec.neki.testePratico.view.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import serratec.neki.testePratico.service.SkillService;
import serratec.neki.testePratico.shared.dto.SkillDto;
import serratec.neki.testePratico.view.model.SkillRequest;
import serratec.neki.testePratico.view.model.SkillResponse;


@RestController
@RequestMapping("/skill")
public class SkillController {

    @Value("${app.caminhoImagens}")
    private String caminhoImagens;

    @Autowired
    private SkillService skillService;

    @GetMapping
    @ApiOperation(value = "Lista todas as skills", notes = "Lista todas as skills")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista skills"),
        @ApiResponse(code = 401, message = "Erro de autenticação"),
        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
        @ApiResponse(code = 404, message = "Recurso não encontrado"),
        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
    })
    public ResponseEntity<List<SkillResponse>> obterTodos(){
        List<SkillDto> skill = skillService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<SkillResponse> resposta = skill.stream()
            .map(skillDto -> mapper.map(skillDto, SkillResponse.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lista skill por Id", notes = "Lista skill")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista skill"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<Optional<SkillResponse>> obterPorId(@PathVariable Long id){
        Optional<SkillDto> dto = skillService.obterPorId(id);
        SkillResponse skill = new ModelMapper().map(dto.get(), SkillResponse.class);

        return new ResponseEntity<>(Optional.of(skill), HttpStatus.OK);
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    @ApiOperation(value = "Cadastra skill", notes = "Cadastra skill")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cadastra skill"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<SkillResponse> adicionar(@Valid @RequestPart("skill") SkillRequest skillReq,
                         @RequestParam("file") MultipartFile arquivo){

       ModelMapper mapper = new ModelMapper();
       SkillDto dto = mapper.map(skillReq, SkillDto.class);
             
    //    dto = skillService.adicionar(dto, arquivo);

       try {
        if (!arquivo.isEmpty()) {
          byte[] bytes = arquivo.getBytes();
          Path caminho = Paths
              .get(caminhoImagens + String.valueOf(dto.getId()) + arquivo.getOriginalFilename());
          Files.write(caminho, bytes);
  
          dto.setImagem(String.valueOf(dto.getId()) + arquivo.getOriginalFilename());
            
          dto = skillService.adicionar(dto, arquivo);
         
        }
  
      } catch (IOException e) {
        e.printStackTrace();
      }
      

       return new ResponseEntity<>(mapper.map(dto, SkillResponse.class), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Altera dados da skill", notes = "Altera dados da skill")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Altera skill"),
        @ApiResponse(code = 401, message = "Erro de autenticação"),
        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
        @ApiResponse(code = 404, message = "Recurso não encontrado"),
        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
    })
    public ResponseEntity<SkillResponse> atualizar(@RequestBody SkillRequest skillReq, @PathVariable Long id){
       
        ModelMapper mapper = new ModelMapper();
        SkillDto skillDto = mapper.map(skillReq, SkillDto.class);

        skillDto = skillService.atualizar(id, skillDto);

        return new ResponseEntity<> (
            mapper.map(skillDto, SkillResponse.class),
            HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta skill", notes = "Deleta skill")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Deleta skill"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<?> deletar(@PathVariable Long id){
        skillService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

    


