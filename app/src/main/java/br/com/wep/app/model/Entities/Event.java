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

    @Column(name = "Title", nullable = false, length = 100)
    private String title;

    @Column(name = "description", nullable = false, length = 600)
    private String description;

    @Column(name = "adress", nullable = false, length = 100)
    private String adress;

    @Column(name = "img", nullable = false, length = 200)
    private String img;

    @Column(name = "privated", nullable = false)
    private boolean privated;

    @Column(name = "createdAt", nullable = false, length = 55)
    private Date createdAt;

    @Column(name = "eventeDate", nullable = false)
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
