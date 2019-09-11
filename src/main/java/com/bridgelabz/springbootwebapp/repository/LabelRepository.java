package com.bridgelabz.springbootwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.springbootwebapp.model.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

}
