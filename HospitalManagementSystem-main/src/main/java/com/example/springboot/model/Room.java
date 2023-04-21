package com.example.springboot.model;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

 

import lombok.Data;

@Entity
@Table(name="room_table")
@Data
@SequenceGenerator(name="room",sequenceName="room_gene",initialValue=7000)
public class Room {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="room")
	@Column(name="room_id")
	private long roomId;
	
	@Column(name="room_number") 
	@NotEmpty
	@Size(min=2 ,max=4, message="roomNumber must be between 2- 4 digits")
	private String roomNumber;
	
	@Column(name="Ward_type") 
	@NotEmpty
	@Size(min=4 ,max=10, message="WardType must have 3 to 4 letter")
	private String WardType;

	
	public long getRoomId() {
		return roomId;
	}


	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}


	public String getRoomNumber() {
		return roomNumber;
	}


	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}


	public String getWardType() {
		return WardType;
	}


	public void setWardType(String wardType) {
		WardType = wardType;
	}


	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", roomNumber=" + roomNumber + ", WardType=" + WardType + "]";
	}


	
	
	
}
