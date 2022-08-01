package com.mcamelo.msgApp.entities;

import com.mcamelo.msgApp.entities.enums.NotificationsType;

import javax.persistence.*;
import java.util.Objects;
import java.io.Serializable;
@Entity
@Table(name = "tb_notification")
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private boolean delivered;
    private boolean readed;

    private NotificationsType notificationsType;

    @ManyToOne
    private User userTo;

    @ManyToOne
    private User userFrom;

    public Notification() {
    }

    public Notification(Long id, String content, boolean delivered, boolean readed, NotificationsType notificationsType, User userTo, User userFrom) {
        this.id = id;
        this.content = content;
        this.delivered = delivered;
        this.readed = readed;
        this.notificationsType = notificationsType;
        this.userTo = userTo;
        this.userFrom = userFrom;
    }

    public NotificationsType getNotificationsType() {
        return notificationsType;
    }

    public void setNotificationsType(NotificationsType notificationsType) {
        this.notificationsType = notificationsType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isReaded() {
        return readed;
    }

    public void setReaded(boolean read) {
        this.readed = read;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User userFrom) {
        this.userFrom = userFrom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notification)) return false;
        Notification that = (Notification) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
