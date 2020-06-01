package br.com.wep.app.Entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class User<E> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "name", nullable = false, length = 55)
    private String name;
    @Column(name = "email", nullable = false, length = 100)
    private String email;
    @Column(name = "password", nullable = false, length = 8)
    private String password;
    @Column(name = "whatssap", nullable = false, length = 11)
    private String whatssap;

    public User() {
    }

    public User(String avatar, String name, String email, String password, String whatssap) {
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.password = password;
        this.whatssap = whatssap;
    }

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWhatssap() {
        return whatssap;
    }

    public void setWhatssap(String whatssap) {
        this.whatssap = whatssap;
    }
}
