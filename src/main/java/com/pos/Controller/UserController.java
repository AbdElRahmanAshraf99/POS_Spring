package com.pos.Controller;

import com.pos.Domain.User;
import com.pos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController
{
	@Autowired
	private UserRepository userRepository;

	@GetMapping("/all")
	public Map<String, Object> getAllUsers()
	{
		Map<String, Object> result = new HashMap<>();
		result.put("fields", Arrays.asList("Code", "Creation Date", "Username", "Email"));
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

	@PostMapping("/save")
	public User addUser(@RequestBody User user)
	{
		return userRepository.save(user);
	}

	@PostMapping("/delete")
	public Boolean deleteUser(@RequestBody String id)
	{
		Optional<User> object = userRepository.findById(Long.valueOf(id));
		if (object.isPresent())
		{
			userRepository.delete(object.get());
			return true;
		}
		return false;
	}

}
