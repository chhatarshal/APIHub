package com.myapp.base.service;

import java.util.List;

import com.myapp.base.model.ideabook.Ideamodel;

public interface IdeaService {
	public String saveIdea(Ideamodel ideaModel);
	public List<Ideamodel> getAllIdeas();
}
