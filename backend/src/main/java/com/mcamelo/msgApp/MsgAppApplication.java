package com.mcamelo.msgApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class MsgAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsgAppApplication.class, args);
		System.out.println(new BCryptPasswordEncoder().encode("123"));
	}

}
