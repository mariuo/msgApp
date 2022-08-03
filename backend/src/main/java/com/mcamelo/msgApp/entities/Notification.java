package com.mcamelo.msgApp.entities;

import com.mcamelo.msgApp.entities.enums.NotificationsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "tb_notification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

}
