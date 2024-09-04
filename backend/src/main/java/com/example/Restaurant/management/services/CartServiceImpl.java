package com.example.Restaurant.management.services;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.dtos.CartGetDto;
import com.example.Restaurant.management.dtos.OrdersDto;
import com.example.Restaurant.management.entities.*;
import com.example.Restaurant.management.repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CartTempRepository cartTempRepository;

    private Orders currentOrder;

    public Orders startOrder() {
        // Create a new order every time a new session starts
        currentOrder = new Orders();
        currentOrder.setCreatedAt(Instant.now());
        currentOrder.setStatus("pending");
        return ordersRepository.save(currentOrder);
    }
    @Override
    public Cart createCart(CartDto cartDto) {
        Cart cart = new Cart();
        CartTemp cartTemp=new CartTemp();

        // Ensure there's an active order
        if (currentOrder == null || currentOrder.isConfirmed()) {
            currentOrder = startOrder(); // Start a new order if none exists or if the last one was confirmed
        }
        User user = userRepository.findById(cartDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Menu menu = menuRepository.findById(cartDto.getMenuId())
                .orElseThrow(() -> new RuntimeException("Menu item not found"));


        cart.setUser(user);
        cart.setMenu(menu);
        cart.setOrders(currentOrder);
        cart.setQuantity(1); // Quantity is explicitly set to 1

        cartRepository.save(cart);

        cartTemp.setCartTempId(cart.getCartId());
        cartTemp.setUser(user);
        cartTemp.setMenu(menu);
        cartTemp.setOrders(currentOrder);
        cartTemp.setQuantity(1); // Quantity is explicitly set to 1

        cartTempRepository.save(cartTemp);

        return cart; // Optionally return the cart or modify to return something else as needed
    }

    @Override
    public boolean deleteCartById(Long id) {
        if (cartRepository.existsById(id) ) {
            cartRepository.deleteById(id);
            cartTempRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<CartGetDto> getAllCartTempByUserId(Long userId) {
        List<CartTemp> cartTemps = cartTempRepository.findByUserId(userId);

        return cartTemps.stream().map(cartTemp -> {
            CartGetDto dto = new CartGetDto();
            dto.setUserId(cartTemp.getUser().getId());
            dto.setCartId(cartTemp.getCartTempId());
            dto.setMenuName(cartTemp.getMenu().getName());
            dto.setMenuPrice(cartTemp.getMenu().getPrice());
            dto.setQuantity(cartTemp.getQuantity());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void confirmOrder(OrdersDto ordersDto) {
        if (currentOrder != null) {
            // Retrieve the CartTemp items associated with the current order
            List<CartTemp> cartItems = cartTempRepository.findByOrders_OrdersId(currentOrder.getOrdersId());

            // Calculate the total price
            double totalPrice = 0.0;
            for (CartTemp item : cartItems) {
                // Get the price of each menu item from the Menu table
                Menu menu = menuRepository.findById(item.getMenu().getMenuId()).orElseThrow(() ->
                        new RuntimeException("Menu item not found for ID: " + item.getMenu().getMenuId()));
                totalPrice += menu.getPrice() * item.getQuantity();
            }

            // Set the total price in the current order
            currentOrder.setTotalPrice(totalPrice);

            // Set additional details from the OrdersDto
            currentOrder.setPaymentType(ordersDto.getPaymentType());
            currentOrder.setOrderType(ordersDto.getOrderType());
            currentOrder.setMobileNumber(ordersDto.getMobileNumber());
            currentOrder.setAddress(ordersDto.getAddress());

            // Confirm the order (set a flag in the order)
            currentOrder.setConfirmed(true);

            // Save the current order with the total price and other details
            ordersRepository.save(currentOrder);

            // Clear the cart
            cartTempRepository.deleteAll();

            // Clear the currentOrder to allow for a new order in the next session
            currentOrder = null;
        }
    }


    @Override
    public CartGetDto increaseCartQuantity(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Increase the quantity by 1
        cart.setQuantity(cart.getQuantity() + 1);
        cartRepository.save(cart);

        CartTemp cartTemp = cartTempRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("CartTemp item not found"));

        // Increase the quantity by 1
        cartTemp.setQuantity(cartTemp.getQuantity() + 1);
        cartTempRepository.save(cartTemp);

        return convertToCartGetDto(cart);
    }

    @Override
    public CartGetDto decreaseCartQuantity(Long cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        CartTemp cartTemp = cartTempRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("CartTemp item not found"));

        // Decrease the quantity but ensure it does not go below 1
        if (cart.getQuantity() > 1 && cartTemp.getQuantity()>1) {
            cart.setQuantity(cart.getQuantity() - 1);
            cartTemp.setQuantity(cartTemp.getQuantity()-1);
            cartRepository.save(cart);
            cartTempRepository.save(cartTemp);
            return convertToCartGetDto(cart);
        } else {
            return null; // If quantity is already 1, return null
        }
    }

//     @Override
//     public void confirmOrder() {
//         if (currentOrder != null) {
//             // Retrieve the CartTemp items associated with the current order
//             List<CartTemp> cartItems = cartTempRepository.findByOrders_OrdersId(currentOrder.getOrdersId());
//
//             // Calculate the total price
//             double totalPrice = 0.0;
//             for (CartTemp item : cartItems) {
//                 // Get the price of each menu item from the Menu table
//                 Menu menu = menuRepository.findById(item.getMenu().getMenuId()).orElseThrow(() ->
//                         new RuntimeException("Menu item not found for ID: " + item.getMenu().getMenuId()));
//                 totalPrice += menu.getPrice() * item.getQuantity();
//             }
//
//             // Set the total price in the current order
//             currentOrder.setTotalPrice(totalPrice);
//
//             // Confirm the order (set a flag in the order)
//             currentOrder.setConfirmed(true);
//
//             // Save the current order with the total price
//             ordersRepository.save(currentOrder);
//
//             // Clear the cart
//             cartTempRepository.deleteAll();
//
//             // Clear the currentOrder to allow for a new order in the next session
//             currentOrder = null;
//         }
//     }

    private CartGetDto convertToCartGetDto(Cart cart) {
        CartGetDto cartGetDto = new CartGetDto();
        cartGetDto.setUserId(cart.getUser().getId());
        cartGetDto.setMenuName(cart.getMenu().getName());
        cartGetDto.setMenuPrice(cart.getMenu().getPrice());
        cartGetDto.setQuantity(cart.getQuantity());
        return cartGetDto;
    }

}
