package com.bridgelabz.fundooapp.service;

import java.util.List;

import com.bridgelabz.fundooapp.dto.LabelDTO;
import com.bridgelabz.fundooapp.model.Label;

public interface LabelService {
	
	public Label createLabel(LabelDTO labelDTO, String token);
	
	public Label updateLabel(LabelDTO labelDTO,Long labelID,String token);
	
	public void deleteLabel(Long labelID,String token);
	
	public List<Label> fetchLabel(String token);
	

}
