package br.com.wep.app.model.Entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Entity diz está classe é uma entidade
@Entity
//@Table referencia a table do banco de dados
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Title", nullable = false, length = 100)
    private String title;
    @Column(name = "description", nullable = false, length = 600)
    private String description;
    @Column(name = "adress", nullable = true, length = 100)
    private String adress;
    @Column(name = "img", columnDefinition = "TEXT")
    private String img;
    @Column(name = "privated", nullable = false)
    private boolean privated;
    @Column(name = "likes")
    private Integer likes = 0;

    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    @Column(name = "eventDate", nullable = false)
    private String eventDate;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @OneToMany(mappedBy = "event")
    //@JsonManagedReference
    private List<Comment> comments;

    @OneToMany
    private List<User> liked;

    @ManyToOne
    //@JsonManagedReference
    private User user;

    public Event() {
    }

    public Event(
            String title, String description,
            String adress, String img,
            boolean privated, String eventDate,
            String latitude, String longitude,
            User user
    ) {
        this.title = title;
        this.description = description;
        this.adress = adress;
        this.img = img;
        this.privated = privated;
        this.eventDate = eventDate;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public boolean getPrivated() {
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

    public Integer getLikes() { return likes; }

    public void setLikes(Integer likes) { this.likes = likes; }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<User> getLiked() { return liked; }

    public void setLiked(List<User> liked) { this.liked = liked; }

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
