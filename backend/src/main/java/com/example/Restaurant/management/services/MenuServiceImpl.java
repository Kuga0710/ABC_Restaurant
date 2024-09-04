package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.MenuDto;
import com.example.Restaurant.management.entities.Menu;
import com.example.Restaurant.management.repositories.MenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Menu createMenu(MenuDto menuDto, MultipartFile file) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDto, menu);

        if (file != null && !file.isEmpty()) {
            try {
                byte[] imageBytes = file.getBytes();
                System.out.println("Image size: " + imageBytes.length);

                menu.setImage(imageBytes);

                if (menu.getImage() == null || menu.getImage().length == 0) {
                    throw new RuntimeException("Failed to set image bytes");
                }

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error processing file", e);
            }
        } else {
            System.out.println("File is null or empty");
        }

        return menuRepository.save(menu);
    }

    @Override
    public List<MenuDto> getMenus(String name) {
        List<Menu> menus = menuRepository.findByNameOrAll(name);
        return menus.stream().map(menu -> {
            MenuDto menuDto = new MenuDto();
            BeanUtils.copyProperties(menu, menuDto);
            return menuDto;
        }).collect(Collectors.toList());
    }

    @Override
    public Menu updateMenu(Long id, MenuDto menuDto) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invalid Menu ID"));

        BeanUtils.copyProperties(menuDto, menu);
        return menuRepository.save(menu);
    }

    @Override
    public boolean deleteMenu(Long id) {
        if (menuRepository.existsById(id)) {
            menuRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }


}
