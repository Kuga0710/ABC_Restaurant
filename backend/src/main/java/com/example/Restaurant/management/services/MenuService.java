package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.MenuDto;
import com.example.Restaurant.management.entities.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MenuService {


    //List<MenuDto> getAllMenus();
    Menu updateMenu(Long id, MenuDto menuDto);
    boolean deleteMenu(Long id);
    Menu createMenu(MenuDto menuDto, MultipartFile file);
    //MenuDto getMenuByName(String name);

    List<MenuDto> getMenus(String name);
}
