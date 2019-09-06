package com.bridgelabz.springbootwebapp.service;

import com.bridgelabz.springbootwebapp.dto.LoginDTO;
import com.bridgelabz.springbootwebapp.dto.RegisterDTO;
import com.bridgelabz.springbootwebapp.dto.ResetPasswordDTO;
import com.bridgelabz.springbootwebapp.model.User;

public interface UserService {

	public User register(RegisterDTO userDTO) throws Exception;

	public String verifyUser(String token) throws Exception;

	public String login(LoginDTO loginDTO) throws Exception;

	public String forgotPassword(String emailID) throws Exception;

	public String resetPassword(ResetPasswordDTO resetPasswordDTO, String token) throws Exception;

}
