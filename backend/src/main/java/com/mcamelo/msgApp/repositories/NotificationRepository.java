package com.mcamelo.msgApp.repositories;

import com.mcamelo.msgApp.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserToIdAndDeliveredFalse(Long id);
}
