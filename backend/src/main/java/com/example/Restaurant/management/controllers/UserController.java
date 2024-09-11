package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.UserDto;
import com.example.Restaurant.management.entities.User;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.UserService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {
        User registeredUser = userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto) {
        String token = userService.verify(userDto);
        if (token != null) {
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    @GetMapping("/users")
    public ResponseEntity<ResponseWrapper<List<UserDto>>> getAllUsersByRole(
            @RequestParam("role") String role) {

        List<UserDto> users = userService.getAllUsersByRole(role);

        if (users != null && !users.isEmpty()) {
            return ResponseEntity.ok(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.RETRIEVED,
                    users
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.NOT_FOUND.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }
    }

    @GetMapping("/users/csv")
    public ResponseEntity<byte[]> getAllUsersByRoleAsCsv(@RequestParam("role") String role) {
        List<UserDto> users = userService.getAllUsersByRole(role);

        if (users != null && !users.isEmpty()) {
            byte[] csvData =userService.generateCsv(users);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=users.csv");
            headers.add(HttpHeaders.CONTENT_TYPE, "text/csv");

            return ResponseEntity.ok().headers(headers).body(csvData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("{Id}")
    public ResponseEntity<ResponseWrapper<User>> updateUser(@PathVariable("id") Long id, @RequestBody UserDto userDto) {
        User updatedUser = userService.updateUser(id, userDto);
        if (updatedUser != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.SAVE_FAILED,
                    null
            ));
        }
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.DELETE_SUCCESS,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.NOT_FOUND.getCode(),
                    ValidationMessages.DELETE_FAILED,
                    null
            ));
        }
    }
}
