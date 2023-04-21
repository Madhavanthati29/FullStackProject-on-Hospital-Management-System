package com.example.springboot.service;

import java.util.List;

import com.example.springboot.model.Admin;

public interface AdminService {

	Admin saveAdmin(Admin admin);

	Admin loginAdmin(Admin admin);

	List<Admin> getAllAdmins();
	
	Admin getAdminById(long adminId);

	void deleteAdmin(long adminId);

	Admin updateAdmin(Admin admin, long adminId);


}
