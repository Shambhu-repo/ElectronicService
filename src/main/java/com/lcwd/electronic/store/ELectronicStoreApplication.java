package com.lcwd.electronic.store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ELectronicStoreApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(ELectronicStoreApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("encoded password is here below for the email : 30shambhu@gmail.com");
		System.out.println(passwordEncoder.encode("Shambu3011"));
	}

}
