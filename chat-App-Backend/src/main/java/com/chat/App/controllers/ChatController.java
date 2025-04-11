package com.chat.App.controllers;

import java.time.LocalDateTime;

import org.springframework.messaging.handler.annotation.DestinationVariable;//
import org.springframework.messaging.handler.annotation.MessageMapping;//
import org.springframework.messaging.handler.annotation.SendTo;//
import org.springframework.stereotype.Controller;//
import org.springframework.web.bind.annotation.CrossOrigin;//
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;//
import com.chat.App.Payload.MessageRequest;//
import com.chat.App.entities.Messages;//
import com.chat.App.entities.Room;//
import com.chat.App.repositories.RoomRepo;

@Controller
//@CrossOrigin("http://localhost:5173")
public class ChatController {


    private final RoomRepo roomRepository;

    public ChatController(RoomRepo roomRepository) {
        this.roomRepository = roomRepository;
    }


    //for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")// /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}")//subscribe
    public Messages sendMessage(
    		@DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) {
    	

        Room room = roomRepository.findByRoomId(request.getRoomId());
        Messages message = new Messages(request.getContent(),request.getSender());
        message.setTimeStamp(LocalDateTime.now());
        if (room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        } else {
            throw new RuntimeException("room not found !!");
        }

        return message;

    }
    
}
