package ir.maktab.twiter.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "application_user")
public class Users extends BaseEntity {

    private int id;
    private String username;
    private String password;
    private boolean deleted;

    public Users(int id) {
        this.id = id;
    }

    public Users(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
