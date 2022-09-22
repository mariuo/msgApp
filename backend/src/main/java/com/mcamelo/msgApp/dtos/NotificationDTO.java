package com.mcamelo.msgApp.dtos;

import com.mcamelo.msgApp.entities.Notification;
import com.mcamelo.msgApp.entities.enums.NotificationsType;

import java.io.Serializable;

public class NotificationDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String content;
    private boolean delivered;
    private boolean readed;
    private NotificationsType notificationsType;
    private UserDTO userToDTO;
    private UserDTO userFromDTO;

    public NotificationDTO(Long id, String content, boolean delivered, boolean readed, NotificationsType notificationsType, UserDTO userToDTO, UserDTO userFromDTO) {
        this.id = id;
        this.content = content;
        this.delivered = delivered;
        this.readed = readed;
        this.notificationsType = notificationsType;
        this.userToDTO = userToDTO;
        this.userFromDTO = userFromDTO;
    }
    public NotificationDTO(Notification entity) {
        id = entity.getId();
        content = entity.getContent();
        delivered = entity.isDelivered();
        readed = entity.isReaded();
        notificationsType = entity.getNotificationsType();
        userToDTO = new UserDTO(entity.getUserTo());;
        userFromDTO = new UserDTO(entity.getUserFrom());
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

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public NotificationsType getNotificationsType() {
        return notificationsType;
    }

    public void setNotificationsType(NotificationsType notificationsType) {
        this.notificationsType = notificationsType;
    }

    public UserDTO getUserToDTO() {
        return userToDTO;
    }

    public void setUserToDTO(UserDTO userToDTO) {
        this.userToDTO = userToDTO;
    }

    public UserDTO getUserFromDTO() {
        return userFromDTO;
    }

    public void setUserFromDTO(UserDTO userFromDTO) {
        this.userFromDTO = userFromDTO;
    }
}
