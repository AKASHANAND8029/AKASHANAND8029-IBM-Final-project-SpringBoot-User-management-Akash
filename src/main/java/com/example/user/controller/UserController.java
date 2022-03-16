package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.service.UserService;
import com.example.user.ui.RequestModel;
import com.example.user.ui.ResponseModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
@Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @PostMapping("/user-create")
    public ResponseEntity<ResponseModel> createTask(@RequestBody RequestModel requestModel)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto=modelMapper.map(requestModel,UserDto.class);

        userDto= userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(userDto,ResponseModel.class));
    }
    @GetMapping("/user-list")
    public ResponseEntity<List<ResponseModel>> getUsers()
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        List<ResponseModel> list=new ArrayList<>();
        List<UserDto> dtos= userService.getUser();
        for (UserDto d:dtos)
        {
            list.add(modelMapper.map(d,ResponseModel.class));
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("/find/{email}")
    public ResponseEntity<ResponseModel> findUserByEmail(@PathVariable("email") String email)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return ResponseEntity.ok(modelMapper.map(userService.findUserByEmail(email),ResponseModel.class));

    }
    @GetMapping("/validate/{email}")
    public ResponseEntity<UserDto> validateUserByEmail(@PathVariable("email") String email)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return ResponseEntity.ok(modelMapper.map(userService.findUserByEmail(email),UserDto.class));

    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<String> delete(@PathVariable("email") String email)
    {
        userService.delete(email);
        return ResponseEntity.ok("deletion Successful");
    }
}
