package com.example.forum.helpers;

import com.example.forum.models.User;
import com.example.forum.models.dtos.RegisterDto;
import com.example.forum.models.dtos.UserDto;
import com.example.forum.models.dtos.UserUpdateDto;
import com.example.forum.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class UserMapper {

    private final UserService userService;

    @Autowired
    public UserMapper(UserService userService) {
        this.userService = userService;
    }

    public User fromDto(int id, UserDto userDto){
        User user = fromUserDto(userDto);
        user.setId(id);
        User repositoryUser = userService.get(id);
        user.setUserComments(repositoryUser.getUserComments());
        user.setUserPosts(repositoryUser.getUserPosts());
        if (repositoryUser.isAdmin()){
            user.setIsAdmin(true);
        }
        if (repositoryUser.isBlocked()){
            user.setIsBlocked(true);
        }
        return user;
    }
    public User fromUpdateDto(int id, UserUpdateDto userDto){
        User user = fromUserUpdateDto(userDto);
        user.setId(id);
        User repositoryUser = userService.get(id);
        user.setUsername(repositoryUser.getUsername());
        user.setUserComments(repositoryUser.getUserComments());
        user.setUserPosts(repositoryUser.getUserPosts());
        user.setIsAdmin(repositoryUser.isAdmin());
        user.setIsBlocked(repositoryUser.isBlocked());
        if (repositoryUser.isAdmin()){
            user.setIsAdmin(true);
        }
        if (repositoryUser.isBlocked()){
            user.setIsBlocked(true);
        }
        return user;
    }
    public User fromUserUpdateDto(UserUpdateDto userDto){
        User user = new User();
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setDateOfRegistration(LocalDateTime.now());
        return user;
    }
    public User fromUserDto(UserDto userDto){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setDateOfRegistration(LocalDateTime.now());
        return user;
    }

    public User fromRegisterDto(RegisterDto registerDto){
        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setEmail(registerDto.getEmail());
        user.setDateOfRegistration(LocalDateTime.now());
        return user;
    }

    public UserDto toDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
    public UserUpdateDto toUpdateDto(User user){
         UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setPassword(user.getPassword());
        userUpdateDto.setFirstName(user.getFirstName());
        userUpdateDto.setLastName(user.getLastName());
        userUpdateDto.setEmail(user.getEmail());
        userUpdateDto.setAdmin(user.isAdmin());
        userUpdateDto.setBlocked(user.isBlocked());
        return userUpdateDto;
    }
}
