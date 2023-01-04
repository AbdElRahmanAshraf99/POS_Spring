package com.pos;

import com.pos.Domain.Address;
import com.pos.Domain.Customer;
import com.pos.Repository.CustomerRepository;
import jakarta.persistence.Persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class PosApplication implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(PosApplication.class, args);
	}

	@Override
	public void run(String... args)
	{

	}
}
