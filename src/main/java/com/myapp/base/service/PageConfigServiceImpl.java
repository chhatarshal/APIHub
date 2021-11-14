package com.myapp.base.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.myapp.base.entity.HighLevelPageConfiguration;
import com.myapp.base.model.HighLevelPageConfigurationModel;
import com.myapp.base.repository.HighLevelPageConfigurationRepository;

public class PageConfigServiceImpl implements PageConfigService {	

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private HighLevelPageConfigurationRepository highLevelPageConfigurationRepository;

	@Override
	public String  pageConfig(HighLevelPageConfigurationModel pageConfigModel) {
		HighLevelPageConfiguration  highLevelPageConfiguration  = convertToPageConfig(pageConfigModel);
		highLevelPageConfigurationRepository.save(highLevelPageConfiguration);
		return "Success";
	}

	@Override
	public List<HighLevelPageConfigurationModel> getPageConfigs() {
		return highLevelPageConfigurationRepository.findAll().stream().map(idea -> convertToPageConfigModel(idea)).collect(Collectors.toList());
	}

	private HighLevelPageConfigurationModel convertToPageConfigModel(HighLevelPageConfiguration highLevelPageConfigurationModel) {
		HighLevelPageConfigurationModel ideaModel = modelMapper.map(highLevelPageConfigurationModel, HighLevelPageConfigurationModel.class);
		return ideaModel;
	}

	private HighLevelPageConfiguration convertToPageConfig(HighLevelPageConfigurationModel ideaModel) {
		HighLevelPageConfiguration highLevelPageConfiguration = modelMapper.map(ideaModel, HighLevelPageConfiguration.class);
		return highLevelPageConfiguration;
	}

}
