package com.myapp.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.myapp.base.entity.Idea;

@Component
public interface IdeaRepository extends JpaRepository<Idea, Long> {

}
