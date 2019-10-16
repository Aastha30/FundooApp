package com.bridgelabz.fundooapp.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.dto.LoginDTO;
import com.bridgelabz.fundooapp.dto.RegisterDTO;
import com.bridgelabz.fundooapp.dto.ResetPasswordDTO;
import com.bridgelabz.fundooapp.dto.UserDetail;
import com.bridgelabz.fundooapp.response.Response;
import com.bridgelabz.fundooapp.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200", exposedHeaders = { "jwt_token" })
@RequestMapping("/fundoo/user")

public class UserController {

	@Autowired
	private UserService userService;

	Response response = new Response();

	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody RegisterDTO registerDTO) throws Exception {
		userService.register(registerDTO);
		response.setStatusCode(200);
		response.setStatusMessage("Registered Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/verify/{token}")
	public ResponseEntity<Response> verifyToken(@PathVariable String token) throws Exception {
		String message = userService.verifyUser(token);
		response.setStatusCode(200);
		response.setStatusMessage(message);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDTO loginDTO, HttpServletResponse httpResponse)
			throws Exception {
		UserDetail userDetail = userService.login(loginDTO);
		// httpResponse.addHeader("jwt_token", token);
		response.setStatusCode(200);
		response.setStatusMessage("You are successfully logged in");
		response.setBody(userDetail);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/forgotpassword")
	public ResponseEntity<Response> forgotPassword(@RequestParam String emailID) throws Exception {
		String message = userService.forgotPassword(emailID);
		response.setStatusCode(202);
		response.setStatusMessage(message);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/resetpassword/{token}")
	public ResponseEntity<Response> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO,
			@PathVariable String token) throws Exception {
		String message = userService.resetPassword(resetPasswordDTO, token);
		response.setStatusCode(200);
		response.setStatusMessage(message);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}
