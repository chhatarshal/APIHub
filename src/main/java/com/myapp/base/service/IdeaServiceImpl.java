package com.myapp.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.base.entity.Idea;
import com.myapp.base.model.ideabook.Ideamodel;
import com.myapp.base.repository.IdeaRepository;

@Service
public class IdeaServiceImpl implements IdeaService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private IdeaRepository ideaRepo;

	@Override
	public String saveIdea(Ideamodel ideaModel) {
		Idea idea = convertToIdea(ideaModel);
		ideaRepo.save(idea);
		return "Success";
	}

	@Override
	public List<Ideamodel> getAllIdeas() {
		return ideaRepo.findAll().stream().map(idea -> convertToIdeaModel(idea)).collect(Collectors.toList());
	}

	private Ideamodel convertToIdeaModel(Idea idea) {
		Ideamodel ideaModel = modelMapper.map(idea, Ideamodel.class);
		return ideaModel;
	}

	private Idea convertToIdea(Ideamodel ideaModel) {
		Idea idea = modelMapper.map(ideaModel, Idea.class);
		return idea;
	}

}
