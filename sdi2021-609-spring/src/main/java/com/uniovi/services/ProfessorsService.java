package com.uniovi.services;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.uniovi.entities.Professor;

@Service
public class ProfessorsService {

	private List<Professor> professorsList = new LinkedList<Professor>();

	@PostConstruct
	public void init() {
		professorsList.add(new Professor(1L, "12345678A", "Edward Ronaldo", "Nuñez Valdez", "SDI"));
		professorsList.add(new Professor(2L, "87654321S", "Enrique", "de la Cal", "SDI"));
	}

	public List<Professor> getProfessors() {
		return professorsList;
	}

	public Professor getProfessor(Long id) {
		return professorsList.stream().filter(Professor -> Professor.getId().equals(id)).findFirst().get();
	}

	public void addProfessor(Professor Professor) {
		// Si en Id es null le asignamos el ultimo + 1 de la lista
		if (Professor.getId() == null) {
			Professor.setId(professorsList.get(professorsList.size() - 1).getId() + 1);
		}
		professorsList.add(Professor);
	}

	public void deleteProfessor(Long id) {
		professorsList.removeIf(Professor -> Professor.getId().equals(id));
	}

}
