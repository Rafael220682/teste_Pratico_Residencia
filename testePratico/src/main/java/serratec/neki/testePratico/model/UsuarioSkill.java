package serratec.neki.testePratico.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@SequenceGenerator(name = "generator_usuarioSkill", sequenceName = "usuarioSkill_seq", initialValue = 1, allocationSize = 1)
@Table(name = "usuarioSkill")
public class UsuarioSkill {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_usuariokill")
    @NotNull
    @Column(name="usuarioSkillId")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "userId")
    private Usuario userId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "skillId")
    private Skill skillId;

    @NotNull
    private int knowledgeLevel;

    @NotNull
    private Date createdAt;

    private Date updateAt;

    public UsuarioSkill() {

    }

    public UsuarioSkill(Long id, Usuario userId, Skill skillId, int knowledgeLevel,
            Date createdAt, Date updateAt) {
        this.id = id;
        this.userId = userId;
        this.skillId = skillId;
        this.knowledgeLevel = knowledgeLevel;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUserId() {
        return userId;
    }

    public void setUserId(Usuario userId) {
        this.userId = userId;
    }

    public Skill getSkillId() {
        return skillId;
    }

    public void setSkillId(Skill skillId) {
        this.skillId = skillId;
    }

    public int getKnowledgeLevel() {
        return knowledgeLevel;
    }

    public void setKnowledgeLevel(int knowledgeLevel) {
        this.knowledgeLevel = knowledgeLevel;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    

    

    


}
