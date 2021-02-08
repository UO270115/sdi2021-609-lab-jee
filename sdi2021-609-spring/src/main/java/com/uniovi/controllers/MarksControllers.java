package com.uniovi.controllers;

import org.springframework.web.bind.annotation.*;

import com.uniovi.entities.Mark;

@RestController
public class MarksControllers {

	@RequestMapping("/mark/list")
	public String getList() {
		return "Getting List";
	}

//	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
//	public String setMark(@RequestParam String description, @RequestParam String score) {
//		return "Added: " + description + " with score: " + score;
//	}

	@RequestMapping(value = "/mark/add", method = RequestMethod.POST)
	public String setMark(@ModelAttribute Mark mark) {
		return "added: " + mark.getDescription() + " with score : " + mark.getScore() + " id: " + mark.getId();
	}

//	@RequestMapping("/mark/details")
//	public String getDetail(@RequestParam Long id) {
//		return " Getting Detail: " + id;
//	}

	@RequestMapping("/mark/details/{id}")
	public String getDetail(@PathVariable Long id) {
		return " Getting Detail: " + id;
	}

}
