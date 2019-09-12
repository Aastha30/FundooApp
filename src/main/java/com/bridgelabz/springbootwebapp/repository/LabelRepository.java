package com.bridgelabz.springbootwebapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.springbootwebapp.model.Label;

public interface LabelRepository extends JpaRepository<Label, Long>{

	Optional<Label> findByUserIDAndLabelID(Long userID, Long labelID);

	List<Label> findByUserID(Long userID);

}
