package com.bridgelabz.springbootwebapp.service;

import com.bridgelabz.springbootwebapp.dto.LoginDTO;
import com.bridgelabz.springbootwebapp.dto.RegisterDTO;
import com.bridgelabz.springbootwebapp.model.User;

public interface UserService {

	public User register(RegisterDTO userDTO) throws Exception;

	public String verifyUser(String token) throws Exception;

	public String login(LoginDTO loginDTO) throws Exception;

}
