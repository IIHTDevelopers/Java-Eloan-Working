package com.iiht.training.eloan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iiht.training.eloan.entity.Users;
import com.iiht.training.eloan.model.UserDto;
import com.iiht.training.eloan.repository.UsersRepository;
import com.iiht.training.eloan.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UsersRepository usersRepository;
	
	@Override
	public UserDto registerClerk(UserDto userDto) {
		Users user = new Users();
		if(userDto.getId() != null) 
			user.setId(userDto.getId());
		
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setMobile(userDto.getMobile());
			user.setRole("Clerk");
			usersRepository.save(user);
			if(user.getId() != null)
				userDto.setId(user.getId());
			return userDto;
		
	}

	@Override
	public UserDto registerManager(UserDto userDto) {
		Users user = new Users();
		if(userDto.getId() != null) 
			user.setId(userDto.getId());
		
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			user.setMobile(userDto.getMobile());
			user.setRole("Manager");
			usersRepository.save(user);
			if(user.getId() != null)
				userDto.setId(user.getId());
			return userDto;
		
	}

	@Override
	public List<UserDto> getAllClerks() {
		List<Users> userList = usersRepository.findAllUsersBasedOnRole("Clerk");
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for(Users user: userList) {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setMobile(user.getMobile());
			dto.setEmail(user.getEmail());
			userDtoList.add(dto);
		}
		return userDtoList;
	}

	@Override
	public List<UserDto> getAllManagers() {
		List<Users> userList = usersRepository.findAllUsersBasedOnRole("Manager");
		List<UserDto> userDtoList = new ArrayList<UserDto>();
		for(Users user: userList) {
			UserDto dto = new UserDto();
			dto.setId(user.getId());
			dto.setFirstName(user.getFirstName());
			dto.setLastName(user.getLastName());
			dto.setMobile(user.getMobile());
			dto.setEmail(user.getEmail());
			userDtoList.add(dto);
		}
		return userDtoList;
	}

}
