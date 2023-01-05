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

import serratec.neki.testePratico.model.Usuario;
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
    public ResponseEntity<List<UsuarioResponse>> obterTodos(){
        List<UsuarioDto> usuario = usuarioService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<UsuarioResponse> resposta = usuario.stream()
            .map(usuarioDto -> mapper.map(usuarioDto, UsuarioResponse.class))
            .collect(Collectors.toList());

            return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<UsuarioResponse>> obterPorId(@PathVariable Long id){
        Optional<UsuarioDto> dto = usuarioService.obterPorId(id);
        UsuarioResponse usuario = new ModelMapper().map(dto.get(), UsuarioResponse.class);

        return new ResponseEntity<>(Optional.of(usuario), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> adicionar(@RequestBody UsuarioRequest usuarioReq){
       ModelMapper mapper = new ModelMapper();

       UsuarioDto dto = mapper.map(usuarioReq, UsuarioDto.class);
             
       dto = usuarioService.adicionar(dto);

       return new ResponseEntity<>(mapper.map(dto, UsuarioResponse.class), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizar(@RequestBody UsuarioRequest usuarioReq, @PathVariable Long id){
       
        ModelMapper mapper = new ModelMapper();
        UsuarioDto usuarioDto = mapper.map(usuarioReq, UsuarioDto.class);

        usuarioDto = usuarioService.atualizar(id, usuarioDto);

        return new ResponseEntity<> (
            mapper.map(usuarioDto, UsuarioResponse.class),
            HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        usuarioService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
