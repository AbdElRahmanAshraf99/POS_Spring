package com.pos.security;

import com.pos.Domain.User;
import com.pos.Repository.UserRepository;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig
{
	@Bean
	public PasswordEncoder encoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepository)
	{
		return username -> {
			User user = userRepository.findByUsername(username);
			if (user != null)
				return user;
			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/login", "/register").permitAll().antMatchers(HttpMethod.POST, "/user/save").permitAll().anyRequest()
				.authenticated();
		http.csrf().disable();
		return http.formLogin().loginPage("/login").defaultSuccessUrl("/").and().logout().logoutSuccessUrl("/login").and().build();
	}
}
