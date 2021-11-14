package com.myapp.base.service;

import java.util.List;

import com.myapp.base.model.HighLevelPageConfigurationModel;

public interface PageConfigService {
	public List<HighLevelPageConfigurationModel> getPageConfigs();
	public String  pageConfig(HighLevelPageConfigurationModel pageConfigModel);
}
