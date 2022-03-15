package com.example.user;

import com.example.user.model.User;
import com.example.user.repo.UserRepository;
import com.example.user.role.RoleType;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class UserApplication implements CommandLineRunner {


    private final UserRepository userRepository;

    public UserApplication(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }

    @Override
    public void run(String... args) throws Exception {
       // userRepository.save(new User(new Random().nextInt(10000),"abc@email.com","ABC","abc123", RoleType.USER));

    }


}
