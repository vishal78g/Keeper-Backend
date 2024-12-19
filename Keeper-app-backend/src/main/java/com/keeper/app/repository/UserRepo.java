package com.keeper.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keeper.app.entity.User;
import java.util.List;



public interface UserRepo extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);
	
	List<User> findByEmail(String email);

}
