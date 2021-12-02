package com.booknotes.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.booknotes.base.entity.Settings;

public interface SettingsRepository extends JpaRepository<Settings, Long> {

}
