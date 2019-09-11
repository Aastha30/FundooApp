package com.bridgelabz.springbootwebapp.service;

import com.bridgelabz.springbootwebapp.dto.LabelDTO;
import com.bridgelabz.springbootwebapp.model.Label;
import com.bridgelabz.springbootwebapp.util.TokenUtil;

public class LabelServiceImpl implements LabelService{

	@Override
	public Label createLabel(LabelDTO labelDTO, String token) {
		TokenUtil.verifyToken(token);
		
		return null;
	}

}
