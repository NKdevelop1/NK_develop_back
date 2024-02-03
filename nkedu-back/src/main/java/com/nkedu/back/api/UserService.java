package com.nkedu.back.api;

import java.util.Optional;

import com.nkedu.back.dto.ParentDto;
import com.nkedu.back.entity.User;
import com.nkedu.back.dto.UserDto;

public interface UserService {

	
	public boolean signup(UserDto userDto);
	
	public boolean signup(ParentDto parentDto);
	
	public Optional<User> getUserWithAuthorities(String username);
	
	public Optional<User> getMyUserWithAuthorities();
	 
}
