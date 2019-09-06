package com.bridgelabz.springbootwebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.springbootwebapp.dto.LoginDTO;
import com.bridgelabz.springbootwebapp.dto.RegisterDTO;
import com.bridgelabz.springbootwebapp.dto.ResetPasswordDTO;
import com.bridgelabz.springbootwebapp.model.User;
import com.bridgelabz.springbootwebapp.service.UserService;

@RestController
@RequestMapping("/fundoo")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/register")
	public User register(@RequestBody RegisterDTO registerDTO) throws Exception {
		return userService.register(registerDTO);
	}

	@GetMapping("/verify/{token}")
	public String verifyToken(@PathVariable String token) throws Exception {
		return userService.verifyUser(token);
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginDTO loginDTO) throws Exception {
		return userService.login(loginDTO);
	}

	@PostMapping("/forgotpassword")
	public String forgotPassword(@RequestParam String emailID) throws Exception {
		return userService.forgotPassword(emailID);
	}

	@PutMapping("/resetpassword/{token}")
	public String resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO, @PathVariable String token)
			throws Exception {
		return userService.resetPassword(resetPasswordDTO, token);
	}
}
