package com.example.notification_service.service;

import com.example.notification_service.auth.UserContextHolder;
import com.example.notification_service.clients.ConnectionsClient;
import com.example.notification_service.dto.PersonDto;
import com.example.notification_service.entity.Notifications;
import com.example.notification_service.repository.NotificationRepository;
import com.example.posts_service.event.PostCreatedEvent;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsServiceConsumer {
    private final ConnectionsClient connectionsClient;
    private final NotificationRepository notificationRepository;
    //private final UserContextHolder userContextHolder;
    @KafkaListener(topics = "post-created-topic")
    public void listen(PostCreatedEvent message) {
        //System.out.println("Received Messasge in group - group-id: " + message);
        List<PersonDto> connections = connectionsClient.getFirstDegree(message.getCreatorId());
        for(PersonDto person : connections){
            System.out.println(person);
            Notifications notifications = new Notifications();
            notifications.setUserId(person.getUserId());
            notifications.setMessage(person.getUserId()+"get new post from"+message.getCreatorId());
            notificationRepository.save(notifications);
        }
    }
}
