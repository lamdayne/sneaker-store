package com.poly.sneakerstore.controller;

import com.poly.sneakerstore.dto.request.CreateUserRequest;
import com.poly.sneakerstore.dto.request.UpdateUserRequest;
import com.poly.sneakerstore.dto.response.ResponseSuccess;
import com.poly.sneakerstore.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseSuccess createUser(@RequestBody @Valid CreateUserRequest request){
        return new ResponseSuccess(
                HttpStatus.CREATED, "User created successfully", userService.createUser(request)
        );
    }

    @PutMapping("/{userId}")
    public ResponseSuccess updateUser(@PathVariable String userId, @RequestBody @Valid UpdateUserRequest request){
        return new ResponseSuccess(
                HttpStatus.ACCEPTED, "User updated successfully", userService.updateUser(userId, request)
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseSuccess deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return new ResponseSuccess(HttpStatus.NO_CONTENT, "User deleted successfully");
    }


    @GetMapping("/{userId}")
    public ResponseSuccess getUserById(@PathVariable("userId") String userId) {
        return new ResponseSuccess(
                HttpStatus.OK, "Get user successfully", userService.getUserById(userId)
        );
    }

    @GetMapping
    public ResponseSuccess getAllUsers(
            @RequestParam(defaultValue = "0", required = false) int pageNo,
            @RequestParam(defaultValue = "8", required = false) int pageSize,
            @RequestParam(required = false) String sortBy
    ){
        return new ResponseSuccess(
                HttpStatus.OK,
                "Get users successfully",
                userService.getAllUsers(pageNo, pageSize, sortBy)
        );
    }

}
