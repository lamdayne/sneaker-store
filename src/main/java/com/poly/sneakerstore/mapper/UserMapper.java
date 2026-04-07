package com.poly.sneakerstore.mapper;

import com.poly.sneakerstore.dto.request.CreateUserRequest;
import com.poly.sneakerstore.dto.request.UpdateUserRequest;
import com.poly.sneakerstore.dto.response.UserResponse;
import com.poly.sneakerstore.model.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = { AddressMapper.class })
public interface UserMapper {
    User toUser(CreateUserRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
        // NOT MAP NULL PROPERTY
    void updateUser(@MappingTarget User user, UpdateUserRequest request);

    UserResponse toUserResponse(User user);

    List<UserResponse> toUserResponseList(List<User> users);
}
