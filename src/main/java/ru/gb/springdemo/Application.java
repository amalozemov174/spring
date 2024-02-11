package ru.gb.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.gb.springdemo.model.User;
import ru.gb.springdemo.model.UserEntity;
import ru.gb.springdemo.repository.UserRepository;

@SpringBootApplication
public class Application {
	static long id = 1L;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		UserRepository userRepository = SpringApplication.run(Application.class, args).getBean(UserRepository.class);
		saveUser(userRepository, "admin");
	}

	private static void saveUser(UserRepository userRepository, String admin) {
		UserEntity user = new UserEntity();
		user.setId(id++);
		user.setLogin(admin);
		user.setPassword(admin);
		user.setRole(admin);
		userRepository.save(user);
	}

}