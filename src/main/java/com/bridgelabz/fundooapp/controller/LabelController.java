package com.bridgelabz.fundooapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundooapp.dto.LabelDTO;
import com.bridgelabz.fundooapp.model.Label;
import com.bridgelabz.fundooapp.response.Response;
import com.bridgelabz.fundooapp.service.LabelService;
import com.bridgelabz.fundooapp.util.TokenUtil;

@RestController
@RequestMapping("/fundoo/label")
@CrossOrigin(origins="http://localhost:4200",exposedHeaders= {"jwt_token"})

public class LabelController {

	@Autowired
	private LabelService labelService;

	@PostMapping("/create")
	public ResponseEntity<Response> createLabel(@RequestBody LabelDTO labelDTO, @RequestHeader String token) {
		Response response = new Response();
		Label label = labelService.createLabel(labelDTO, token);
		response.setStatusCode(200);
		response.setStatusMessage("Label Created Successfully");
		response.setBody(label);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateLabel(@RequestBody Label label,@RequestHeader String token) {
		Response response = new Response();
		Label updatedLabel = labelService.updateLabel(label, token);
		response.setStatusCode(200);
		response.setStatusMessage("Label Updated Successfully");
		response.setBody(updatedLabel);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{labelID}")
	public ResponseEntity<Response> deleteLabel(@PathVariable Long labelID, @RequestHeader String token) {
		System.out.println(labelID);
		Response response = new Response();
		labelService.deleteLabel(labelID, token);
		response.setStatusCode(200);
		response.setStatusMessage("Label Deleted Successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);

	}
	
	@GetMapping("/fetch")
	public ResponseEntity<Response> fetchLabel(@RequestHeader String token)
	{
		Response response =new Response();
		List<Label> labels=labelService.fetchLabel(token);
		response.setStatusCode(200);
		response.setStatusMessage("Fetched Labels of user ID: "+TokenUtil.verifyToken(token));
		response.setBody(labels);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}

}
