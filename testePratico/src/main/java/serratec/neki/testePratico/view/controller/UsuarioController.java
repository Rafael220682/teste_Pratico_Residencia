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
import serratec.neki.testePratico.service.UsuarioService;
import serratec.neki.testePratico.shared.dto.UsuarioDto;
import serratec.neki.testePratico.view.model.UsuarioRequest;
import serratec.neki.testePratico.view.model.UsuarioResponse;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @ApiOperation(value = "Lista todos usuários", notes = "Lista todos os usuários")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista usuários"),
        @ApiResponse(code = 401, message = "Erro de autenticação"),
        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
        @ApiResponse(code = 404, message = "Recurso não encontrado"),
        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
    })
    public ResponseEntity<List<UsuarioResponse>> obterTodos(){
        List<UsuarioDto> usuario = usuarioService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<UsuarioResponse> resposta = usuario.stream()
            .map(usuarioDto -> mapper.map(usuarioDto, UsuarioResponse.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Lista usuário por Id", notes = "Lista usuário")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Lista usuário"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<Optional<UsuarioResponse>> obterPorId(@PathVariable Long id){
        Optional<UsuarioDto> dto = usuarioService.obterPorId(id);
        UsuarioResponse usuario = new ModelMapper().map(dto.get(), UsuarioResponse.class);

        return new ResponseEntity<>(Optional.of(usuario), HttpStatus.OK);
    }

    @PostMapping
    @ApiOperation(value = "Cadastra usuário", notes = "Cadastra usuário")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Cadastra usuário"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<UsuarioResponse> adicionar(@RequestBody UsuarioRequest usuarioReq){
       ModelMapper mapper = new ModelMapper();

       UsuarioDto dto = mapper.map(usuarioReq, UsuarioDto.class);
             
       dto = usuarioService.adicionar(dto);

       return new ResponseEntity<>(mapper.map(dto, UsuarioResponse.class), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Altera dados do usuário", notes = "Altera dados do usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Altera usuário"),
        @ApiResponse(code = 401, message = "Erro de autenticação"),
        @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
        @ApiResponse(code = 404, message = "Recurso não encontrado"),
        @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
    })
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody UsuarioRequest usuarioReq, @PathVariable Long id){
       
        ModelMapper mapper = new ModelMapper();
        UsuarioDto usuarioDto = mapper.map(usuarioReq, UsuarioDto.class);

        usuarioDto = usuarioService.atualizar(id, usuarioDto);

        return new ResponseEntity<> (
            mapper.map(usuarioDto, UsuarioResponse.class),
            HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta usuário", notes = "Deleta usuário")
    @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Deleta usuário"),
      @ApiResponse(code = 401, message = "Erro de autenticação"),
      @ApiResponse(code = 403, message = "Não há permissão para acessar o recurso"),
      @ApiResponse(code = 404, message = "Recurso não encontrado"),
      @ApiResponse(code = 505, message = "Exceção interna da aplicação"),
  })
    public ResponseEntity<?> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
