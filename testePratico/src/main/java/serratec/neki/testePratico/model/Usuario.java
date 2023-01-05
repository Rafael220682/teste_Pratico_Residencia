package serratec.neki.testePratico.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@SequenceGenerator(name = "generator_user", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
@Table(name = "usuario")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_user")
    @NotNull
    @Column(name="userId")
    private Long id;

    @Column(length = 12, unique = true)  
    @NotNull  
    private String login;

    @Column(length = 100)
    @NotNull
    private String password;
    
    private Date lastLoginDate;

    @ManyToMany
    @JoinTable(name = "usuarioSkill",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name="skillId"))
    private List<Skill> skill = new ArrayList<Skill>();
    
    public Usuario() {
    }

    public Usuario(Long id, String login, String password, Date lastLoginDate, List<Skill> skill) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
    }

    public Usuario(String login, String password, Date lastLoginDate, List<Skill> skill) {
        this.login = login;
        this.password = password;
        this.lastLoginDate = lastLoginDate;
    }

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
