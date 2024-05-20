package Controll.Entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private User user;

    @Column(nullable = false)
    private String content;

    @Column(name = "sendDate", nullable = false)
    private Timestamp sendDate;

    // Constructors
    public Message() {}

    public Message(Room room, User user, String content, Timestamp sendDate) {
        this.room = room;
        this.user = user;
        this.content = content;
        this.sendDate = sendDate;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendDate() {
        return sendDate;
    }

    public void setSendDate(Timestamp sentDate) {
        this.sendDate = sentDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", room=" + room +
                ", user=" + user +
                ", content='" + content + '\'' +
                ", sentDate=" + sendDate +
                '}';
    }
}
