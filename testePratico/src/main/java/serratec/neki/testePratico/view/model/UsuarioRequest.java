package serratec.neki.testePratico.view.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UsuarioRequest {
    
    private String login;   
    private String password;    

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "GMT-3" )
    private Date lastLoginDate; 
   
    
    
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

  
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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

   
}
