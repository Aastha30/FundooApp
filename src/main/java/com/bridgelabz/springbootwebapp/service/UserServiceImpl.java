package com.bridgelabz.springbootwebapp.service;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.springbootwebapp.dto.LoginDTO;
import com.bridgelabz.springbootwebapp.dto.RegisterDTO;
import com.bridgelabz.springbootwebapp.dto.ResetPasswordDTO;
import com.bridgelabz.springbootwebapp.model.User;
import com.bridgelabz.springbootwebapp.repository.UserRepository;
import com.bridgelabz.springbootwebapp.util.EmailUtil;
import com.bridgelabz.springbootwebapp.util.GenerateURL;
import com.bridgelabz.springbootwebapp.util.TokenUtil;

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
		User user = modelMapper.map(registerDTO, User.class);
		LocalDateTime currentTime = LocalDateTime.now();
		user.setCreatedTime(currentTime);
		user.setUpdatedTime(currentTime);
		user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
		user = userRepository.save(user);
		String url = GenerateURL.getUrl("verify", user.getId());
		EmailUtil.sendEmail(registerDTO.getEmailID(), "Mail Verification", url);
		return user;

	}

	@Override
	public String verifyUser(String token) throws Exception {
		Long id = TokenUtil.verifyToken(token);
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("Invalid Token"));
		user.setVerified(true);
		userRepository.save(user);
		return "Token Verified Successfully";
	}

	@Override
	public String login(LoginDTO loginDTO) throws Exception {
		User user = userRepository.findByEmailID(loginDTO.getEmailID())
				.orElseThrow(() -> new Exception("User is not Registered"));
		if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword()) && user.isVerified())
			return "You are successfully logged in";
		else if (!user.isVerified()) {
			EmailUtil.sendEmail(loginDTO.getEmailID(), "Email Verification", TokenUtil.generateToken(user.getId()));
			return "Validation is required! Please check your email to verify.";
		} else {
			return "Incorrect EmailID/Password";
		}
	}

	@Override
	public String forgotPassword(String emailID) throws Exception {
		User user = userRepository.findByEmailID(emailID).orElseThrow(() -> new Exception("EmailID not found"));
		String url = GenerateURL.getUrl("resetpassword", user.getId());
		EmailUtil.sendEmail(emailID, "Password Reset", url);
		return "Password reset link has been sent to the registered email.";
	}

	@Override
	public String resetPassword(ResetPasswordDTO resetPasswordDTO, String token) throws Exception {
		Long id = TokenUtil.verifyToken(token);
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("Invalid Token"));
		if (resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
			user.setPassword(passwordEncoder.encode(resetPasswordDTO.getConfirmPassword()));
			userRepository.save(user);
			return "Your password is successfully updated! You can login with your new password now.";
		} else {
			return "Please enter the password fields correctly.";
		}

	}
}
