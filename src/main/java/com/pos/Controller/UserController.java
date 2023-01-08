package com.pos.Controller;

import com.pos.Domain.Customer;
import com.pos.Domain.User;
import com.pos.Repository.CustomerRepository;
import com.pos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/all")
	public List<Map<String, String>> getAllCustomers()
	{
		List<User> users = (List<User>) userRepository.findAll();
		List<Map<String, String>> resultList = new ArrayList<>();
		for (User user : users)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", user.getId().toString());
			resultMap.put("Code", user.getCode());
			resultMap.put("Creation Date", user.getCreationDate().toLocalDate().toString());
			resultMap.put("Username", user.getUsername());
			resultMap.put("Email", user.getEmail());
			resultList.add(resultMap);
		}
		return resultList;
	}

}
