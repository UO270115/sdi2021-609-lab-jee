package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;
import com.uniovi.repositories.ProfessorsRepository;

@Service
public class ProfessorsService {

	@Autowired
	private ProfessorsRepository professorsRepository;

	public List<Professor> getProfessors() {
		List<Professor> professors = new ArrayList<Professor>();
		professorsRepository.findAll().forEach(professors::add);
		return professors;
	}

	public Professor getProfessor(Long id) {
		return professorsRepository.findById(id).get();
	}

	public void addProfessor(Professor professor) {
		professorsRepository.save(professor);
	}

	public void deleteProfessor(Long id) {
		professorsRepository.deleteById(id);
	}

}
