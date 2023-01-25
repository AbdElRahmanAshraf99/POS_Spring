package com.pos;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PosApplication implements CommandLineRunner
{

	public static void main(String[] args)
	{
		SpringApplication.run(PosApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException
	{
		Runtime rt = Runtime.getRuntime();
		rt.exec("rundll32 url.dll,FileProtocolHandler " + "http://localhost:8080/");
	}
}
