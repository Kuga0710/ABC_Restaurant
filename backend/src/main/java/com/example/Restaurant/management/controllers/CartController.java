package com.example.Restaurant.management.controllers;

import com.example.Restaurant.management.dtos.CartDto;
import com.example.Restaurant.management.dtos.CartGetDto;
import com.example.Restaurant.management.dtos.OrdersDto;
import com.example.Restaurant.management.entities.Cart;
import com.example.Restaurant.management.enums.RestApiResponseStatusCodes;
import com.example.Restaurant.management.services.CartService;
import com.example.Restaurant.management.utilities.ResponseWrapper;
import com.example.Restaurant.management.utilities.ValidationMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<ResponseWrapper<Cart>> createCart(@RequestBody CartDto cartDto) {
        Cart createdCart = cartService.createCart(cartDto);

        if (createdCart != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.CREATED.getCode(),
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


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseWrapper<Void>> deleteCartById(@PathVariable Long id) {
        boolean isDeleted = cartService.deleteCartById(id);

        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.DELETE_SUCCESS,
                    null
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.DELETE_FAILED,
                    null
            ));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseWrapper<List<CartGetDto>>> getAllCartsByUserId(@PathVariable Long userId) {
        List<CartGetDto> carts = cartService.getAllCartTempByUserId(userId);

        if (carts != null && !carts.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.RETRIEVED,
                    carts
            ));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.NOT_FOUND.getCode(),
                    ValidationMessages.RETRIEVED_FAILED,
                    null
            ));
        }
    }

    @PutMapping("/{cartId}/increase")
    public ResponseEntity<ResponseWrapper<CartGetDto>> increaseQuantity(@PathVariable Long cartId) {
        CartGetDto updatedCart = cartService.increaseCartQuantity(cartId);

        if (updatedCart != null) {
            return ResponseEntity.ok(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.UPDATE_SUCCESS,
                    updatedCart
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.UPDATE_FAILED,
                    null
            ));
        }
    }

    @PutMapping("/{cartId}/decrease")
    public ResponseEntity<ResponseWrapper<CartGetDto>> decreaseQuantity(@PathVariable Long cartId) {
        CartGetDto updatedCart = cartService.decreaseCartQuantity(cartId);

        if (updatedCart != null) {
            return ResponseEntity.ok(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.SUCCESS.getCode(),
                    ValidationMessages.UPDATE_SUCCESS,
                    updatedCart
            ));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseWrapper<>(
                    RestApiResponseStatusCodes.BAD_REQUEST.getCode(),
                    ValidationMessages.UPDATE_FAILED,
                    null
            ));
        }
    }

//    @PostMapping("/confirm-order")
//    public ResponseEntity<Void> confirmOrder() {
//        cartService.confirmOrder();
//        return ResponseEntity.ok().build();
//    }
    @PostMapping("/confirm-order")
    public ResponseEntity<Void> confirmOrder(@RequestBody OrdersDto ordersDto) {
        cartService.confirmOrder(ordersDto);
        return ResponseEntity.ok().build();
}

}
