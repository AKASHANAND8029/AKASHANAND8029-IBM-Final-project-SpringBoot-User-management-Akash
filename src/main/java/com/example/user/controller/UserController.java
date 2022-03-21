package com.example.user.controller;

import com.example.user.dto.UserDto;
import com.example.user.model.User;
import com.example.user.role.RoleType;
import com.example.user.service.UserService;
import com.example.user.ui.RequestModel;
import com.example.user.ui.ResponseModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    public UserController( UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;

    }

    @Operation(summary = "user_management api create new users")
    @ApiResponse(responseCode = "201",description = "created successfully")
    @PostMapping("/user-create")
    public ResponseEntity<ResponseModel> createTask(@RequestBody RequestModel requestModel)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDto userDto=modelMapper.map(requestModel,UserDto.class);

        userDto= userService.createUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(userDto,ResponseModel.class));
    }

    @Operation(summary = "user_management api getting list of user")
    @ApiResponse(responseCode = "201",description = "fetched successfully")
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

    @Operation(summary = "user_management api find by email")
    @ApiResponse(responseCode = "201",description = "found successfully")
    @GetMapping("/user-find/{email}")
    public ResponseEntity<ResponseModel> findUserByEmail(@PathVariable("email") String email)
    {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return ResponseEntity.ok(modelMapper.map(userService.findUserByEmail(email),ResponseModel.class));

    }

    @Operation(summary = "user_management api validate by email")
    @ApiResponse(responseCode = "201",description = "validated successfully")
    @GetMapping("/user-validate/{email}")
    public Boolean validateUserByEmail(@PathVariable("email") String email)
    {
       UserDto dto=userService.validateUserByEmail(email);
       if(dto==null){
           return false;
       }
       else {
           return true;
       }
    }


    @Operation(summary = "user_management api login users")
    @ApiResponse(responseCode = "201",description = "logged in successfully")
    @PostMapping("/login")
    public User loginUser(@RequestBody User user) throws Exception {
        String tempEmailId = user.getEmail();
        String tempPass = user.getPassword();
        RoleType temprole = user.getUserRole();
        User userObj = null;
        if(tempEmailId !=null && tempPass != null )
        {
            userObj =userService.findByEmailAndPasswordAndUserRole(tempEmailId,tempPass,temprole);
        }
        if(userObj == null) {
            throw new Exception("Wrong Credentials!");
        }
        return userObj;
    }

    @Operation(summary = "user_management api delete users")
    @ApiResponse(responseCode = "201",description = "deleted successfully")
    @DeleteMapping("/user-delete/{email}")
    public ResponseEntity<String> delete(@PathVariable("email") String email)
    {
        userService.delete(email);
        return ResponseEntity.ok("deletion Successful");
    }

    @Operation(summary = "user_management api update users")
    @ApiResponse(responseCode = "201",description = "updated successfully")
    @PutMapping("/update-password/{email}")
    public ResponseEntity<ResponseModel> updatePassword(@RequestBody RequestModel requestModel,@PathVariable("email") String email )
    {
        return  ResponseEntity.ok(modelMapper.map(userService.updatePasswordByEmail(requestModel,email),ResponseModel.class));
        }
    }
