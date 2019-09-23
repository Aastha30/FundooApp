package com.bridgelabz.fundooapp.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundooapp.dto.LabelDTO;
import com.bridgelabz.fundooapp.exception.UserException;
import com.bridgelabz.fundooapp.model.Label;
import com.bridgelabz.fundooapp.repository.LabelRepository;
import com.bridgelabz.fundooapp.util.TokenUtil;

@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	private LabelRepository labelRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Label createLabel(LabelDTO labelDTO, String token) {
		Label label = modelMapper.map(labelDTO, Label.class);
		Long userID = TokenUtil.verifyToken(token);
		label.setUserID(userID);
		labelRepository.save(label);
		return label;
	}

	@Override
	public Label updateLabel(LabelDTO labelDTO, Long labelID, String token) {
		Long userID = TokenUtil.verifyToken(token);
		Optional<Label> labelToBeUpdated = labelRepository.findByUserIDAndLabelID(userID, labelID);
		Label updatedLabel = labelToBeUpdated.map(existingLabel -> {
			existingLabel.setLabelName(
					labelDTO.getLabelName() != null ? labelDTO.getLabelName() : labelToBeUpdated.get().getLabelName());
			return existingLabel;
		}).orElseThrow(() -> new UserException(404, "Label Not Found"));
		return labelRepository.save(updatedLabel);
	}

	@Override
	public void deleteLabel(Long labelID, String token) {
		TokenUtil.verifyToken(token);
		try
		{
		labelRepository.deleteById(labelID);
		}
		catch(Exception e)
		{
			throw new UserException(404, "Label ID: " + labelID + " Not Found");
		}
		
	}

	@Override
	public List<Label> fetchLabel(String token) {
		try
		{
		Long userID=TokenUtil.verifyToken(token);
		return labelRepository.findByUserID(userID);
		}
		catch(Exception e)
		{
			throw new UserException(404, "Token Not Found");
		}
		
	}

}
