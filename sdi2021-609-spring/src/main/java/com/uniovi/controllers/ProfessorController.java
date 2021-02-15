package com.uniovi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uniovi.entities.Professor;
import com.uniovi.services.ProfessorsService;

@RestController
public class ProfessorController {

	@Autowired
	private ProfessorsService professorsService;

	@RequestMapping("/professor/list")
	public String getListProfessors() {
		// return "Getting List of professors";
		return professorsService.getProfessors().toString();
	}

	@RequestMapping("/professor/add")
	public String setProfessor(@ModelAttribute Professor professor) {
		professorsService.addProfessor(professor);
		return "Professor added.\nDni: " + professor.getDni() + "\nNombre: " + professor.getNombre() + 
				"\nApellidos: " + professor.getApellidos() + "\nCategoría: " + professor.getCategoria();
	}

	@RequestMapping("/professor/details/{id}")
	public String getDetail(@PathVariable Long id) {
		// return "Details of a professor";
		return professorsService.getProfessor(id).toString();
	}

	@RequestMapping("/professor/edit/{id}")
	public String getEdit(@PathVariable Long id) {
		return "Getting the information of a professor to edit";
	}

	@RequestMapping(value="/professor/edit/{id}", method=RequestMethod.POST)
	public String setEdit(@PathVariable Long id, @ModelAttribute Professor professor) {
		professor.setId(id);
		professorsService.addProfessor(professor);
		return "Editing the information of a professor";
	}

	@RequestMapping("/professor/delete/{id}")
	public String deleteProfessor(@PathVariable Long id) {
		professorsService.deleteProfessor(id);
		return "Professor deleted";
	}

}
