package serratec.neki.testePratico.view.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import serratec.neki.testePratico.model.Skill;

public class UsuarioResponse {
    
    private Long id;  
    private String login;   
    private String password;    

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3" )
    private Date lastLoginDate;  
    private List<Skill> skill = new ArrayList<Skill>();
    
    
    // public UsuarioDto() {
    // }

    // public UsuarioDto(Long id, String login, String password, LocalDate lastLoginDate, List<Skill> skill) {
    //     this.id = id;
    //     this.login = login;
    //     this.password = password;
    //     this.lastLoginDate = lastLoginDate;
    // }

    // public UsuarioDto(String login, String password, LocalDate lastLoginDate, List<Skill> skill) {
    //     this.login = login;
    //     this.password = password;
    //     this.lastLoginDate = lastLoginDate;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public List<Skill> getSkill() {
        return skill;
    }

    public void setSkill(List<Skill> skill) {
        this.skill = skill;
    }
}
