package com.chat.App.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.chat.App.entities.Room;

public interface RoomRepo extends MongoRepository<Room, String>{
	
	Room findByRoomId(String roomId);
}
