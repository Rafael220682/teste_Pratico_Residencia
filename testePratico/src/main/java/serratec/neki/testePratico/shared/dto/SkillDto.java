package serratec.neki.testePratico.shared.dto;

import java.util.ArrayList;
import java.util.List;



import serratec.neki.testePratico.model.Usuario;

public class SkillDto {
    
    
    private Long id;

    private String name;
   
    private String version;
   
    private String description;
   
    private String imagem;
    
    private List<Usuario> usuario = new ArrayList<Usuario>();
     
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public List<Usuario> getUsuario() {
        return usuario;
    }

    public void setUsuario(List<Usuario> usuario) {
        this.usuario = usuario;
    }
}
