package serratec.neki.testePratico.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@SequenceGenerator(name = "generator_skill", sequenceName = "skill_seq", initialValue = 1, allocationSize = 1)
@Table(name = "skill")
public class Skill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_skill")
    @NotNull
    @Column(name="skillId")
    private Long id;

    @Column(length = 100)
    @NotNull
    private String name;

    @Column(length = 10)
    private String version;

    @Column(length = 255)
    @NotNull
    private String description;

    @Column(length = 255)
    private String imagem;

    @ManyToMany(mappedBy = "skill")
    @JsonBackReference
    private List<Usuario> usuario = new ArrayList<Usuario>();
    

    public Skill() {

    }

    public Skill(Long id, String name, String version, String description, String imagem, List<Usuario> usuario) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.description = description;
        this.imagem = imagem;
    }

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
