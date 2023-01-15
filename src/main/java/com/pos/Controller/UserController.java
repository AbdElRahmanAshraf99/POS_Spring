package com.pos.Controller;

import com.pos.Domain.User;
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
	public Map<String, Object> getAllCustomers()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Creation Date", "Username", "Email"));
		List<User> users = (List<User>) userRepository.findAll();
		List<Map<String, String>> values = new ArrayList<>();
		for (User user : users)
		{
			Map<String, String> resultMap = new HashMap<>();
			resultMap.put("Id", user.getId().toString());
			resultMap.put("Code", user.getCode());
			resultMap.put("Creation Date", user.getCreationDate().toLocalDate().toString());
			resultMap.put("Username", user.getUsername());
			resultMap.put("Email", user.getEmail());
			values.add(resultMap);
		}
		result.put("values", values);
		return result;
	}

	@GetMapping("/fieldsInfo")
	public Map<String, Object> getCustomerFieldsInfo()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", List.of("Code", "Username", "Email"));
		Map<String, String> fieldsInfo = new HashMap<>();
		fieldsInfo.put("Code", "text");
		fieldsInfo.put("Username", "text");
		fieldsInfo.put("Email", "email");
		result.put("info", fieldsInfo);
		return result;
	}

}
