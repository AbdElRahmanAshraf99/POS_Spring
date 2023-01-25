package com.pos.security;

import com.pos.Domain.User;
import com.pos.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService
{
	private final UserRepository userRepo;

	@Autowired
	public MyUserDetailsService(UserRepository userRepo)
	{
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = userRepo.findByUsername(username);
		if (user != null)
			return user;
		throw new UsernameNotFoundException("User '" + username + "' not found");
	}
}
