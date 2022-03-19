package com.example.user.service;

import com.example.user.dto.UserDto;
import com.example.user.model.User;
import com.example.user.repo.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private UserServiceImpl(UserRepository userRepository,ModelMapper modelMapper) {
        this.userRepository=userRepository;
        this.modelMapper=modelMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user=modelMapper.map(userDto,User.class);
        StringBuilder stringBuilder=new StringBuilder(userDto.getPassword());
        user.setUserId(new Random().nextInt(10000));
        user.setPassword(stringBuilder.reverse().toString());
        user= userRepository.save(user);

        return modelMapper.map(user,UserDto.class);
    }



    @Override
    public List<UserDto> getUser() {
        List<UserDto> list=new ArrayList<>();
        Iterable<User> iterable=userRepository.findAll();
        Iterator<User> iterator=iterable.iterator();
        while (iterator.hasNext())
        {
            list.add(modelMapper.map(iterator.next(),UserDto.class));
        }
        //if(list.isEmpty()){
          //  throw new EmptyListException("list is empty");
        //}
        return list;
    }

    @Override
    public UserDto findUserByEmail(String email) {
        User user=finduserByEmail(email);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public void delete(String email) {
        User user=finduserByEmail(email);
        if(user==null)
        {
            System.out.println("User doesnot exist");
        }
        userRepository.delete(user);
    }

    @Override
    public UserDto validateUserByEmail(String email) {
        User user=finduserByEmail(email);
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public User findByEmailAndPasswordAndUserRole(String email, String password, RoleType role) {
        return userRepository.findByEmailAndPasswordAndUserRole(email,password, role);
    }

    private User finduserByEmail(String email){
        User user=userRepository.findByEmail(email);
        return  user;
    }



}
