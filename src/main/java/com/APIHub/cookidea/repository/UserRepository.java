package com.APIHub.cookidea.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.APIHub.cookidea.entity.User;
 
@Component
public interface UserRepository extends JpaRepository<User,Long> {
	
	public List<User> findByUserName(String userName);
}
