package com.bridgelabz.fundooapp.service;

import java.time.LocalDateTime;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.dto.LoginDTO;
import com.bridgelabz.fundooapp.dto.RegisterDTO;
import com.bridgelabz.fundooapp.dto.ResetPasswordDTO;
import com.bridgelabz.fundooapp.dto.UserDetail;
import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.User;
import com.bridgelabz.fundooapp.repository.UserRepository;
import com.bridgelabz.fundooapp.util.EmailUtil;
import com.bridgelabz.fundooapp.util.GenerateURL;
import com.bridgelabz.fundooapp.util.TokenUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User register(RegisterDTO registerDTO) throws Exception {

		if (userRepository.findByEmailID(registerDTO.getEmailID()).isPresent()
				|| userRepository.findByMobNum(registerDTO.getMobNum()).isPresent()) {
			throw new UserException(400, "Duplicate User Details Found");
		}
		User user = modelMapper.map(registerDTO, User.class);
		LocalDateTime currentTime = LocalDateTime.now();
		user.setCreatedTime(currentTime);
		user.setUpdatedTime(currentTime);
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		user = userRepository.save(user);
		String url = GenerateURL.getUrl("verify", user.getUserID());
		EmailUtil.sendEmail(registerDTO.getEmailID(), "Mail Verification", url);
		return user;

	}

	@Override
	public String verifyUser(String token) throws Exception {
		Long id = TokenUtil.verifyToken(token);
		User user = userRepository.findById(id).orElseThrow(() -> new UserException(400, "Invalid Token"));
		user.setVerified(true);
		userRepository.save(user);
		return "Token Verified Successfully";
	}

	@Override
	public UserDetail login(LoginDTO loginDTO) throws Exception {
		UserDetail userDetail = new UserDetail();
		User user = userRepository.findByEmailID(loginDTO.getEmailID())
				.orElseThrow(() -> new UserException(404, "User is not Registered"));
		if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()) && user.isVerified()) {
			userDetail.setFirstName(user.getFirstName());
			userDetail.setLastName(user.getLastName());
			userDetail.setEmailID(user.getEmailID());
			userDetail.setToken(TokenUtil.generateToken(user.getUserID()));
			return userDetail;
		} else if (!user.isVerified()) {
			String url = GenerateURL.getUrl("verify", user.getUserID());
			EmailUtil.sendEmail(loginDTO.getEmailID(), "Email Verification", url);
			throw new UserException(404, "Validation is required! Please check your email to verify.");
		} else {
			throw new UserException(401, "Incorrect EmailID/Password");
		}
	}

	@Override
	public String forgotPassword(String emailID) throws Exception {
		User user = userRepository.findByEmailID(emailID)
				.orElseThrow(() -> new UserException(404, "EmailID not found"));
		String url = GenerateURL.getUrl("resetpassword", user.getUserID());
		EmailUtil.sendEmail(emailID, "Password Reset", url);
		return "Password reset link has been sent to the registered email.";
	}

	@Override
	public String resetPassword(ResetPasswordDTO resetPasswordDTO, String token) throws Exception {
		Long id = TokenUtil.verifyToken(token);
		User user = userRepository.findById(id).orElseThrow(() -> new UserException(400, "Invalid Token"));
		if (resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
			user.setPassword(passwordEncoder.encode(resetPasswordDTO.getConfirmPassword()));
			user.setUpdatedTime(LocalDateTime.now());
			userRepository.save(user);
			return "Your password is successfully updated! You can login with your new password now.";
		} else {
			throw new UserException(401, "Please enter the password fields correctly.");
		}

	}
}
