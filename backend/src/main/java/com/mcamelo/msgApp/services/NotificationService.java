package com.mcamelo.msgApp.services;

import com.mcamelo.msgApp.dtos.NotificationDTO;
import com.mcamelo.msgApp.dtos.PostDTO;
import com.mcamelo.msgApp.entities.Notification;
import com.mcamelo.msgApp.entities.Post;
import com.mcamelo.msgApp.repositories.NotificationRepository;
import com.mcamelo.msgApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
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
}
