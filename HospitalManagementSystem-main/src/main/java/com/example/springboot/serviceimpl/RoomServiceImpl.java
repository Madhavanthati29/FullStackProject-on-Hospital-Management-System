package com.example.springboot.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.Room;
 
import com.example.springboot.repository.RoomRepository;
 
import com.example.springboot.service.RoomService;

@Service
public class RoomServiceImpl implements RoomService{

	 
	
	@Autowired
	private RoomRepository roomRepository;
	
	
	@Override
	public Room addRoom(Room room) {
		 
		 
		
		return roomRepository.save(room);
		
		
	}

	@Override
	public List<Room> getAllRooms() {
		
		return roomRepository.findAll();
	}

	@Override
	public Room getRoomById(long roomId) {
		 
		return roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room","roomId",roomId));
	}

	@Override
	public Room updateRoom(Room room, long roomId ) {
		
		Room oldRoom = roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room","roomId",roomId));
		 
		oldRoom.setRoomNumber(room.getRoomNumber());
		oldRoom.setWardType(room.getWardType());
		
		 
	 
		
		return roomRepository.save(oldRoom);
	}

	@Override
	public void deleteRoom(long roomId) {
		Room deleteRoom= roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("Room","roomId",roomId));
		roomRepository.delete(deleteRoom);
		
	}

}
