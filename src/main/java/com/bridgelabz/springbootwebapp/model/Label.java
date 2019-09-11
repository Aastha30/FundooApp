package com.bridgelabz.springbootwebapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Label {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long lableID;
	private Long noteID;
	@Column(nullable = false)
	private String labelName;
	@ManyToMany(mappedBy = "labels")
	private List<Note> notes;

}
