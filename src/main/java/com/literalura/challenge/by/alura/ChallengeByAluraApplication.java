package com.literalura.challenge.by.alura;

import com.literalura.challenge.by.alura.principal.Principal;
import com.literalura.challenge.by.alura.service.libroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class ChallengeByAluraApplication implements CommandLineRunner {


	@Autowired
	private libroService service;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeByAluraApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {
		Principal principal=new Principal(service);
		principal.getMain();

	}
}
