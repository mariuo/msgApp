package com.mcamelo.msgApp.controllers;

import com.mcamelo.msgApp.dtos.NotificationDTO;
import com.mcamelo.msgApp.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<List<NotificationDTO>> getNotificationByUserId(@PathVariable Long id){
        return ResponseEntity.ok().body(notificationService.getAllByUserId(id));
    }

    @GetMapping("/stream/{id}")
    public Flux<ServerSentEvent<List<NotificationDTO>>> streamPosts(@PathVariable Long id) {
        return notificationService.streamNotification(id);
    }


}
