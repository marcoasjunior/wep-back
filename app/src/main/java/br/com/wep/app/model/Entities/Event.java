package br.com.wep.app.model.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Entity diz está classe é uma entidade
@Entity
//@Table referencia a table do banco de dados
@Table(name = "events")
public class Event {

    //@Id diz que este atributo é a chava primaria da entidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //Os @Column são "configurações" das colunas da tabela
    @Column(name = "Title", nullable = false, length = 100)
    private String title;
    @Column(name = "description", nullable = false, length = 600)
    private String description;
    @Column(name = "adress", nullable = true, length = 100)
    private String adress;
    @Column(name = "img", nullable = false, length = 200)
    private String img;
    @Column(name = "privated", nullable = false)
    private boolean privated;

    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @Column(name = "eventeDate", nullable = false)
    private String eventeDate;

    @OneToMany(mappedBy = "event")
    private List<Comment> comments;

    @ManyToOne
    private User user;

    public Event() {
    }

    public Event(String title, String description, String adress, String img, boolean privated, String eventeDate, User user) {
        this.title = title;
        this.description = description;
        this.adress = adress;
        this.img = img;
        this.privated = privated;
        this.eventeDate = eventeDate;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isPrivated() {
        return privated;
    }

    public void setPrivated(boolean privated) {
        this.privated = privated;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getEventeDate() {
        return eventeDate;
    }

    public void setEventeDate(String eventeDate) {
        this.eventeDate = eventeDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(Comment comment) {
        this.comments.add(comment);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
