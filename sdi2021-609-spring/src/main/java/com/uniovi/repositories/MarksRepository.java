package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Mark;

public interface MarksRepository extends CrudRepository<Mark, Long>{
	
	// al extender de CrudRepository, esta clase ya es un Bean

}
