package br.com.wep.app.model.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String adress;
    private String img;
    private boolean privated;
    private Date createdAt;
    private Date eventeDate;
    @OneToMany(mappedBy = "event")
    private List<Comment> comments;
    @ManyToOne
    private User user;
    @OneToMany
    private List<User> likes;
    private int confimed;

    public Event() {
    }

    public Event(String title, String description, String adress, String img, boolean privated, Date eventeDate, User user) {
        this.title = title;
        this.description = description;
        this.adress = adress;
        this.img = img;
        this.privated = privated;
        this.eventeDate = eventeDate;
        this.user = user;
    }
}
