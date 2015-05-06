/**
 *
 */
package org.xiaoqiaotq.domain;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author xiaoqiaotq@gmail.com
 * @date 2014年11月27日
 */
@Entity
@Table(name="t_user")
public class User extends BaseEntity{
    @NotNull
    @Email
    private String username;


    @NotNull
    private String pass;

    private String photo;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private Date registerDate;

    private Role role;

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User [id=" + getId() + ", username=" + username
                + ", date=" + registerDate + "]";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public enum Habit {
        Basketball, Football, Swimming;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static enum Role{
        USER,ADMIN
    }
}
