package serratec.neki.testePratico.shared.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import serratec.neki.testePratico.model.Skill;
import serratec.neki.testePratico.model.Usuario;

public class UsuarioSkillDto {

    
    private Long id;
   
    private Usuario userId;
    
    private Skill skillId;
  
    private int knowledgeLevel;
   
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3" )
    private Date createdAt;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3" )
    private Date updateAt;
   
    
    
    public UsuarioSkillDto() {
    }

    public UsuarioSkillDto(Long id, Usuario userId, Skill skillId, int knowledgeLevel, Date createdAt, Date updateAt) {
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
