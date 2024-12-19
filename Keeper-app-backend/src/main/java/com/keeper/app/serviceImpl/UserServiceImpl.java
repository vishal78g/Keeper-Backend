package com.keeper.app.serviceImpl;

import org.modelmapper.ModelMapper;
import com.keeper.app.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.keeper.app.entity.User;
import com.keeper.app.payload.UserDto;
import com.keeper.app.repository.UserRepo;
import com.keeper.app.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);
	
	@Autowired
    private JWTServiceImpl jwtService;

    @Autowired
    private AuthenticationManager authManager;
	
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		if(!userRepo.existsByEmail(userDto.getEmail())) {
			User user = mapper.map(userDto, User.class);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User newUser=userRepo.save(user);
			return mapper.map(newUser, UserDto.class);
		}
		return null;
		
	}

	@Override
	public UserDto updateUser(Integer id, UserDto userDto) {
		User user = userRepo.findById(id).
				orElseThrow(() ->new ResourceNotFoundException("User","user id" , id));
		user.setEmail(userDto.getEmail());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		User updatedUser=userRepo.save(user);
		return mapper.map(updatedUser, UserDto.class);
	}

	@Override
	public UserDto getUser(Integer id) {
		User user = userRepo.findById(id).
					orElseThrow(() ->new ResourceNotFoundException("User","user id" , id));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public boolean deleteUser(Integer id) {
		User user = userRepo.findById(id).
				orElseThrow(() ->new ResourceNotFoundException("User","user id" , id));
		userRepo.delete(user);
		return true;
		
	}

	@Override
	public boolean isUserRegistered(String Email) {
		return userRepo.existsByEmail(Email);
	}

	@Override
	public UserDto getUserByEmail(String email) {
		User user=userRepo.findByEmail(email).get(0);
		return mapper.map(user, UserDto.class);
	}
	
	@Override
	public String verify(UserDto user) {
        Authentication authentication =
                authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(user.getEmail());
        }
        return "fail";
    }

}
