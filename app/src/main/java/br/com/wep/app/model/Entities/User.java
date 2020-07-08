package br.com.wep.app.model.Entities;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
    @Column(name = "avatar")
    private String avatar;
    @Column(name = "name", nullable = false, length = 55)
    private String name;
    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "whatssap", nullable = false, length = 11)
    private String whatssap;

    @OneToMany(mappedBy = "user")
    private List<Event> myEvents;

    @OneToMany(mappedBy = "user")
    private List<Comment> myComments;


    @ManyToMany
    private List<User> friends;

    public User() {
    }

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String avatar, String name, String email, String password, String whatssap) {
        this.avatar = avatar;
        this.name = name;
        this.email = email;
        this.password = password;
        this.whatssap = whatssap;
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

    public String getWhatssap() {
        return whatssap;
    }

    public void setWhatssap(String whatssap) {
        this.whatssap = whatssap;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(User friend) {
        this.friends.add(friend);
    }

    public List<Event> getMyEvents() {
        return myEvents;
    }

    public void setMyEvents(Event event) {
        this.myEvents.add(event);
    }

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
}
