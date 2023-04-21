package com.example.springboot.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot.model.Admin;
import com.example.springboot.model.Doctor;
import com.example.springboot.model.Receptionist;
import com.example.springboot.model.Room;
import com.example.springboot.service.AdminService;
import com.example.springboot.service.DoctorService;
import com.example.springboot.service.ReceptionistService;
import com.example.springboot.service.RoomService;
 

@CrossOrigin(origins="http://localhost:4200")

//http://localhost:8088/api/admin

@RestController

@RequestMapping("/api/admin")

public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private ReceptionistService receptionistService;
 	
	@Autowired
	private RoomService roomService;
	
	@PostMapping("/register")
	public ResponseEntity<Admin> saveAdmin(@Valid @RequestBody Admin admin){
		System.out.println("Admin Registration Succesfull "+admin);
		return new ResponseEntity<Admin>(adminService.saveAdmin(admin),HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Admin> loginAdmin(@RequestBody Admin admin){
		return new ResponseEntity<Admin>(adminService.loginAdmin(admin),HttpStatus.OK);
	}
	//get all admin details
	@GetMapping
	public List<Admin> getAllAdmins(){
		return adminService.getAllAdmins();
	}
	//get admin by id
	@GetMapping("{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable("adminId") long adminId){
		
		return new ResponseEntity<Admin>(adminService.getAdminById(adminId),HttpStatus.OK);
	}
	
	//updating admin details
	@PutMapping("{adminId}")
	public ResponseEntity<Admin> updateAdmin(@PathVariable("adminId") long adminId, @RequestBody Admin admin) {
	
		return new ResponseEntity<Admin>(adminService.updateAdmin(admin,adminId),HttpStatus.OK);
	}
	//delete by id
	@DeleteMapping("{adminId}")
	public ResponseEntity<Boolean> deleteAdmin(@PathVariable("adminId") long adminId){
		adminService.deleteAdmin(adminId);
		boolean flag = true;
		return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
		
	}
	
	@PostMapping("/register/doctor")
	public ResponseEntity<Doctor> saveAdmin(@Valid @RequestBody Doctor doctor){
		System.out.println("Doctor Registration Succesfull "+doctor);
		return new ResponseEntity<Doctor>(doctorService.saveDoctor(doctor),HttpStatus.CREATED);
	}
	
		//get all doctor details
		@GetMapping("/doctor")
		public List<Doctor> getAllDoctors(){
			return doctorService.getAllDoctors();
		}
		
		//get doctor by id
		@GetMapping("/doctor/{doctorId}")
		public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") long doctorId){
			
			return new ResponseEntity<Doctor>(doctorService.getDoctorById(doctorId),HttpStatus.OK);
		}
		//updating doctor details
		@PutMapping("/doctor/{doctorId}")
		public ResponseEntity<Doctor> updateDoctor(@PathVariable("doctorId") long doctorId, @RequestBody Doctor doctor) {
		
			return new ResponseEntity<Doctor>(doctorService.updateDoctor(doctor,doctorId),HttpStatus.OK);
		}
		//delete by id
		@DeleteMapping("/doctor/{doctorId}")
		public ResponseEntity<Boolean> deleteDoctor(@PathVariable("doctorId") long doctorId){
			doctorService.deleteDoctor(doctorId);
			boolean flag = true;
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
			
		}
		
		@PostMapping("/register/receptionist")
		public ResponseEntity<Receptionist> saveReceptionist(@Valid @RequestBody Receptionist receptionist){
			System.out.println("Receptionist Registration Succesfull "+receptionist);
			return new ResponseEntity<Receptionist>(receptionistService.saveReceptionist(receptionist),HttpStatus.CREATED);
		}
		
		//get all receptionist details
		@GetMapping("/receptionist")
		public List<Receptionist> getAllReceptionists(){
			return receptionistService.getAllReceptionists();
		}
		
		//get receptionist by id
		@GetMapping("/receptionist/{receptionistId}")
		public ResponseEntity<Receptionist> getReceptionistById(@PathVariable("receptionistId") long receptionistId){
			
			return new ResponseEntity<Receptionist>(receptionistService.getReceptionistById(receptionistId),HttpStatus.OK);
		}
		//updating reeptionist details
		@PutMapping("/receptionist/{receptionistId}")
		public ResponseEntity<Receptionist> updateReceptionist(@PathVariable("receptionistId") long receptionistId, @RequestBody Receptionist receptionist) {
		
			return new ResponseEntity<Receptionist>(receptionistService.updateReceptionist(receptionist,receptionistId),HttpStatus.OK);
		}
		//delete by id
		@DeleteMapping("/receptionist/{receptionistId}")
		public ResponseEntity<Boolean> deleteReceptionist(@PathVariable("receptionistId") long receptionistId){
			receptionistService.deleteReceptionist(receptionistId);
			boolean flag = true;
			return new ResponseEntity<Boolean>(flag, HttpStatus.OK);
			
		}
		
		
		 
		
		//adding room 
		
		@PostMapping("/room/register")
		public ResponseEntity<Room> addRoom(@Valid @RequestBody Room room){
			System.out.println("Room Added Succesfully "+room);
			return new ResponseEntity<Room>(roomService.addRoom(room),HttpStatus.CREATED);
		}
		
		@GetMapping("/room")
		public List<Room> getAllRooms(){
			return roomService.getAllRooms();
		}
		
		//get room by id
		@GetMapping("/room/{roomId}")
		public ResponseEntity<Room> getRoomById(@PathVariable("roomId") long roomId){
			
			return new ResponseEntity<Room>(roomService.getRoomById(roomId),HttpStatus.OK);
		}
		//updating room details
		@PutMapping("/room/{roomId}")
		public ResponseEntity<Room> updateRoom(@PathVariable("roomId") long roomId, @RequestBody Room room ) {
		
			return new ResponseEntity<Room>(roomService.updateRoom(room,roomId),HttpStatus.OK);
		}
		//delete by id
		@DeleteMapping("/room/{roomId}")
		public ResponseEntity<String> deleteRoom(@PathVariable("roomId") long roomId){
			roomService.deleteRoom(roomId);
			String message = "Room Details Deleted from Database ";
			return new ResponseEntity<String>(message, HttpStatus.OK);
			
		}
		
		
		
		
}
