package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.ReviewDto;
import com.example.Restaurant.management.dtos.UserDto;
import com.example.Restaurant.management.entities.User;
import com.example.Restaurant.management.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto, user, "id","role");
        return userRepository.save(user);
    }



    @Override
    public User updateUser(Long id, UserDto userDto) {
        // Find the existing user by ID
        User user = userRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Invalid User ID"));

        BeanUtils.copyProperties(userDto, user);
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<UserDto> getAllUsersByRole(String role) {
        List<User> users = userRepository.findByRole(role);
        return users.stream().map(user -> {
        UserDto dto=new UserDto();
        BeanUtils.copyProperties(user, dto);
            return dto;
        }).collect(Collectors.toList());
    }

}
