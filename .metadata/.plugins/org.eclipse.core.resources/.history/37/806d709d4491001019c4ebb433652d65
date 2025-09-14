package com.securebank.controller;

import java.util.List;

import com.securebank.model.User;
import com.securebank.service.UserService;

public class UserController {
	
	private UserService userService = new UserService();
	
	 public void createUser(int id, String fName, String lName, String email) {
	        User user = new User(id, fName, lName, email);
	        userService.saveUser(user);
	}
	 public void deleteUser(int id) {
		 userService.deleteUser(id);
	 }
	
	 public void updateUser(int id, String fName, String lName, String email) {
	       
	        userService.updateUser(id, fName, lName, email);
	}
	 
	 public void viewAllUsers() {
	       
	       
	        List<User> users = userService.getAllUsers();
	        if (users == null || users.isEmpty()) {
	            System.out.println("No users found.");
	            return;
	        }

	        System.out.println("=== List of Users ===");
	        for (User user : users) {
	            System.out.println("ID: " + user.getId() + " | " + user);
	        }
	}
}
