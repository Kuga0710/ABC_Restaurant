package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.MenuDto;
import com.example.Restaurant.management.entities.Menu;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.MenuService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/menu")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Menu>> createMenu(@RequestParam("file") MultipartFile file, @RequestParam("menu") String menuDtoString) throws JsonProcessingException {
        MenuDto menuDto = new ObjectMapper().readValue(menuDtoString, MenuDto.class);

        Menu createdMenu = menuService.createMenu(menuDto, file);

        if (createdMenu != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    createdMenu
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.SAVE_FAILED,
                    null
            ));
        }
    }


    @GetMapping
    public ResponseEntity<ResponseWrapper<List<MenuDto>>> getMenus(@RequestParam(required = false) String name) {
        List<MenuDto> menus = menuService.getMenus(name);
        if (menus.isEmpty() && name != null && !name.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.NOT_FOUND.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }
        return ResponseEntity.ok(new ResponseWrapper<>(
                RestApiResponseStatusCodes.SUCCESS.getCode(),
                ValidationMessages.RETRIEVED,
                menus
        ));
    }



    @PutMapping("{id}")
    public ResponseEntity<ResponseWrapper<Menu>> updateMenu(@PathVariable("id") Long id, @RequestBody MenuDto menuDto) {
        Menu updatedMenu = menuService.updateMenu(id, menuDto);
        if (updatedMenu != null) {
            return ResponseEntity.ok(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.SAVED_SUCCESSFULL,
                    updatedMenu
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.SAVE_FAILED,
                    null
            ));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteMenu(@PathVariable Long id) {
        boolean deleted = menuService.deleteMenu(id);
        if (deleted) {
            return ResponseEntity.ok(new ResponseWrapper<>(
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
