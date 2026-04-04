package com.poly.sneakerstore.service.impl;

import com.poly.sneakerstore.dto.request.CreateUserRequest;
import com.poly.sneakerstore.dto.request.UpdateUserRequest;
import com.poly.sneakerstore.dto.response.PageResponse;
import com.poly.sneakerstore.dto.response.UserResponse;
import com.poly.sneakerstore.exception.AppException;
import com.poly.sneakerstore.exception.ErrorCode;
import com.poly.sneakerstore.mapper.UserMapper;
import com.poly.sneakerstore.model.Role;
import com.poly.sneakerstore.model.User;
import com.poly.sneakerstore.repository.UserRepository;
import com.poly.sneakerstore.service.UserService;
import com.poly.sneakerstore.util.PageableUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PageableUtil pageableUtil;

    @Override
    public UserResponse createUser(CreateUserRequest request) {
        User user = userMapper.toUser(request);

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTS);
        } else if (userRepository.existsByPhone(user.getPhone())) {
            throw new AppException(ErrorCode.PHONE_EXISTS);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatarUrl("https://files.catbox.moe/lonccy.jpg"); // AVATAR DEFAULT
        user.setActive(true);
        user.setRole(Role.USER);
        user.setCreatedAt(LocalDateTime.now());
        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public UserResponse updateUser(String userId, UpdateUserRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (!user.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new AppException(ErrorCode.EMAIL_EXISTS);
        }

        if (!user.getPhone().equals(request.getPhone()) && userRepository.existsByPhone(request.getPhone())) {
            throw new AppException(ErrorCode.PHONE_EXISTS);
        }

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setActive(false);
        userRepository.save(user);
    }

    @Override
    public UserResponse getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    @Override
    public PageResponse<?> getAllUsers(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = pageableUtil.createPageable(pageNo, pageSize, sortBy);
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> response = users.stream().map(userMapper::toUserResponse).toList();

        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(users.getTotalPages())
                .totalElements((int) users.getTotalElements())
                .items(response)
                .build();
    }
}
