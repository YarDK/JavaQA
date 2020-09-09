package mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mantis_user_table")
public class UsersData {
    @Column(name = "username")
    @Type(type = "text")
    private String username;

    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "password")
    @Type(type = "text")
    private String password;

    @Id
    @Column(name = "id")
    private int id;

    public String getUsername() {
        return username;
    }

    public UsersData withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersData withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UsersData withPassword(String password) {
        this.password = password;
        return this;
    }

    public int getId() {
        return id;
    }

    public UsersData withId(int id) {
        this.id = id;
        return this;
    }


    @Override
    public String toString() {
        return "UsersData{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
