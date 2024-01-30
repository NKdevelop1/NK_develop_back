package com.nkedu.back.api;

import java.util.Optional;

import com.nkedu.back.model.User;
import com.nkedu.back.model.UserDto;

public interface UserService {

	
	public boolean signup(UserDto userDto);
	
	public Optional<User> getUserWithAuthorities(String username);
	
	public Optional<User> getMyUserWithAuthorities();
	 
}
