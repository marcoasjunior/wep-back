package br.com.wep.app.model.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "avatar", columnDefinition = "TEXT")
    private String avatar;
    @Column(name = "name", nullable = false, length = 55)
    private String name;
    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "whatsapp", nullable = false, length = 11)
    private String whatsapp;

    @OneToMany(mappedBy = "user")
    //@JsonBackReference
    private List<Event> myEvents;

    @OneToMany(mappedBy = "user")
    //@JsonBackReference
    private List<Comment> myComments;

    @OneToMany(mappedBy = "user")
    //@JsonBackReference
    private List<Like> liked;

    @ManyToMany
    private List<User> following;

    @ManyToMany
    private List<User> followers;

    @ManyToMany
    private List<Event> confirmedsEvents;

    public User() {
    }

    //Auth user
    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    //Register user
    public User(String avatar, String name, String email, String password, String whatsapp) {
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.password = password;
        this.whatsapp = whatsapp;
    }

    public Integer getId() {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    //Quem você segue
    @JsonIgnore
    public List<User> getFollowing(){return this.following;}
    public boolean setFollowing(User new_follow){return this.following.add(new_follow);}
    public boolean removeFollowing(User follow){return this.following.remove(follow);}

    //Quem segue você
    @JsonIgnore
    public List<User> getFollowers(){return this.followers;}
    public boolean setFollowers(User new_follower){return this.followers.add(new_follower);}
    public boolean removeFollowers(User follower){return this.followers.remove(follower);}

    @JsonIgnore
    public List<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(Event event) {
        this.myEvents.add(event);
    }

    @JsonIgnore
    public List<Comment> getMyComments() {
        return myComments;
    }

    public void setMyComments(Comment comment) {
        this.myComments.add(comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }

//    @JsonIgnore
    public List<Event> getConfirmedsEvents() {
        return confirmedsEvents;
    }

    public void setConfirmedsEvents(Event confirmedsEvents) {
        this.confirmedsEvents.add(confirmedsEvents);
    }
}
