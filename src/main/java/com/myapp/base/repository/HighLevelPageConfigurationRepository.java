package com.myapp.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.myapp.base.entity.HighLevelPageConfiguration;

@Component
public interface HighLevelPageConfigurationRepository  extends JpaRepository<HighLevelPageConfiguration, Long> {

}
