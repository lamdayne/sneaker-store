package com.poly.sneakerstore.service;

import com.poly.sneakerstore.dto.request.CreateUserRequest;
import com.poly.sneakerstore.dto.request.UpdateUserRequest;
import com.poly.sneakerstore.dto.response.PageResponse;
import com.poly.sneakerstore.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse updateUser(String userId, UpdateUserRequest request);
    void deleteUser(String userId);
    UserResponse getUserById(String userId);
    PageResponse<?> getAllUsers(int pageNo, int pageSize, String sortBy);
}
