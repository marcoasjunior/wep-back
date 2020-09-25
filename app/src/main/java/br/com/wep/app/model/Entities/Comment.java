package br.com.wep.app.model.Entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;


@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "comment", nullable = false)
    private String comment;

    @ManyToOne
    ///@JsonManagedReference
    private User user;

    @ManyToOne
    //@JsonBackReference
    private Event event;

    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Comment() {
    }

    public Comment(String comment){
        this.comment = comment;
    }

    public Comment(String comment, Event event, User user) {
        this.comment = comment;
        this.event = event;
        this.user = user;
    }


    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @JsonIgnore
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
