package com.myapp.base.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.base.model.ideabook.Ideamodel;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/idea")
public class IdeaBookControler {
	
	List<Ideamodel> ideas = new ArrayList<>();
	
	@PostMapping("/saveIdea")
	public String saveMyIdea(@RequestBody Ideamodel ideamodel) {
		this.ideas.add(ideamodel);
		return "Success";
	}
	
	@GetMapping("/getIdeas")
	public List<Ideamodel> getMyIdeas() {
		return this.ideas;
	}
	
	

}
