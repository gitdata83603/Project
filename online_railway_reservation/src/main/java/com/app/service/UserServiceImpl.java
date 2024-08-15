package com.app.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.custom_exceptions.AuthenticationException;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.AuthRequest;
import com.app.dto.RegisterDto;
import com.app.dto.UserRespDTO;
import com.app.entities.Role;
import com.app.entities.User;


@Service
@Transactional
public class UserServiceImpl implements UserService {
	// depcy
	@Autowired
	private UserDao userDao;
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserRespDTO authenticateUser(AuthRequest dto) {
		// 1.invoke dao 's method
		//Optional<User> user =userDao.findByEmailAndPassword(dto.getEmail(),dto.getPwd());//.orElseThrow(() -> new AuthenticationException("Invalid Email or Password !!!!!!"));
		//valid login -user : persistent -> entity -> dto
		
		Optional<User> users= userDao.findByEmailAndPassword(dto.getEmail(), dto.getPwd());
		
		
		UserRespDTO user1=new UserRespDTO();
		if(users.isPresent())
		{
		User user=users.get();
		user1.setEmail(user.getEmail());
		user1.setRole(user.getRole().name().toUpperCase());
		user1.setFirstName(user.getFirstName());
		user1.setId(user.getId());
		user1.setStatus("success");
		return user1 ;
		}
		user1.setStatus("failure");
		return user1;
	}

	@Override
	public ApiResponse addUser(RegisterDto request) {
		
		User user=mapper.map(request, User.class);
		user.setRole(Role.valueOf("CUSTOMER"));
		
		userDao.save(user);
		return new ApiResponse("Registered Successfully");
	}

}
