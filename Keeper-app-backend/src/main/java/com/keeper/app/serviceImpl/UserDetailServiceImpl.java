package com.keeper.app.serviceImpl;


import com.keeper.app.entity.User;
import com.keeper.app.entity.UserPrincipal;
import com.keeper.app.payload.UserDto;
import com.keeper.app.repository.UserRepo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=this.userRepo.findByEmail(email).get(0);
        return new UserPrincipal(user);
    }
}
