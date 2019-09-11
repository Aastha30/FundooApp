package com.bridgelabz.springbootwebapp.service;

import com.bridgelabz.springbootwebapp.dto.LabelDTO;
import com.bridgelabz.springbootwebapp.model.Label;

public interface LabelService {
	
	public Label createLabel(LabelDTO labelDTO, String token);
	

}
