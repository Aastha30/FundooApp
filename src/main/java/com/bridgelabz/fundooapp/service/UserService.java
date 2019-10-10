package com.bridgelabz.fundooapp.service;

import com.bridgelabz.fundooapp.dto.LoginDTO;
import com.bridgelabz.fundooapp.dto.RegisterDTO;
import com.bridgelabz.fundooapp.dto.ResetPasswordDTO;
import com.bridgelabz.fundooapp.dto.UserDetail;
import com.bridgelabz.fundooapp.model.User;

public interface UserService {

	public User register(RegisterDTO userDTO) throws Exception;

	public String verifyUser(String token) throws Exception;

	public UserDetail login(LoginDTO loginDTO) throws Exception;

	public String forgotPassword(String emailID) throws Exception;

	public String resetPassword(ResetPasswordDTO resetPasswordDTO, String token) throws Exception;

}
