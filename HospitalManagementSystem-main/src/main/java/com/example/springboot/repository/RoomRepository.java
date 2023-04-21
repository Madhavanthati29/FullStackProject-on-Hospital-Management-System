package com.example.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.model.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {

}
