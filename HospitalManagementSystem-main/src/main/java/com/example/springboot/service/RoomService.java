package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Room;

public interface RoomService {

	Room addRoom(Room room);

	List<Room> getAllRooms();

	Room getRoomById(long roomId);

	Room updateRoom(Room room, long roomId );

	void deleteRoom(long roomId);

}
