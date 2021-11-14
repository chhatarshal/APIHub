package com.myapp.base.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.base.model.ideabook.Ideamodel;
import com.myapp.base.service.IdeaService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/idea")
public class IdeaBookControler {
	
	
	@Autowired
	IdeaService ideaService;
	
	@PostMapping("/saveIdea")
	public String saveMyIdea(@RequestBody Ideamodel ideamodel) {
		return ideaService.saveIdea(ideamodel);
	}
	
	@GetMapping(path = "/getIdeas", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Ideamodel> getMyIdeas() {
		return ideaService.getAllIdeas();
	}
	
	

}
