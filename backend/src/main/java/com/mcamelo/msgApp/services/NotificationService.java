package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.NotificationDTO;
import com.mcamelo.msgApp.entities.Notification;
import com.mcamelo.msgApp.repositories.NotificationRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import com.mcamelo.msgApp.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public List<NotificationDTO> getAllByUserId(Long id){
        List<Notification> listPO = notificationRepository.findByUserToIdAndDeliveredFalse(id);
        if(listPO == null){
            return null;
        }
        List<NotificationDTO> notiDTOList = new ArrayList<>(listPO.size());
        for(Notification p : listPO){
            Notification entity = new Notification(
                    p.getId(),
                    p.getContent(),
                    p.isDelivered(),
                    p.isReaded(),
                    p.getNotificationsType(),
                    p.getUserTo(),
                    p.getUserFrom()
                    );
            final NotificationDTO dto = new NotificationDTO(entity);
            notiDTOList.add(dto);
        }
        return notiDTOList;
    }
    @Transactional
    public NotificationDTO addNotification(NotificationDTO dto){
        Notification entity = new Notification();
        entity.setContent(dto.getContent());
        entity.setDelivered(dto.isDelivered());
        entity.setReaded(dto.isReaded());
        entity.setNotificationsType(dto.getNotificationsType());
        entity.setUserTo(userRepository.getOne(dto.getUserToDTO().getId()));
        entity.setUserFrom(userRepository.getOne(dto.getUserFromDTO().getId()));
        entity = notificationRepository.save(entity);
        return new NotificationDTO(entity);
    }

    @ReadOnlyProperty
    public Flux<ServerSentEvent<List<NotificationDTO>>> streamNotification(Long id) {
        var userFound = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User not found") );
        if(id != null && userFound != null){
            return Flux.interval(Duration.ofSeconds(2))
                .publishOn(Schedulers.boundedElastic())
                .map(sequence -> ServerSentEvent.<List<NotificationDTO>>builder().id(String.valueOf(sequence))
                        .event("sse-notify")
                        .data(getAllByUserId(id))
                        .build());
        }
        return null;
//        return Flux.interval(Duration.ofSeconds(1))
//                .map(seq -> ServerSentEvent.<List<NotificationDTO>>builder().id(String.valueOf(seq))
//                        .event("sse-notify")
//                        .data(new ArrayList<>())
//                        .build());

    }

//    @ReadOnlyProperty
//    public Flux<ServerSentEvent<List<PostDTO>>> streamPosts() {
//        return Flux.interval(Duration.ofSeconds(2))
//                .publishOn(Schedulers.boundedElastic())
//                .map(sequence -> ServerSentEvent.<List<PostDTO>>builder().id(String.valueOf(sequence))
//                        .event("post-event")
//                        .data(getAllPosts())
//                        .build());
//    }
}
