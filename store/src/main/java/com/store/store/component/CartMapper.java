package com.store.store.component;

import com.store.store.domain.entity.Cart;
import com.store.store.domain.entity.CartItem;
import com.store.store.domain.entity.Product;
import com.store.store.dto.CartDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartMapper {
    // 엔티티 -> DTO 변환
    public CartDTO.ItemResponse toItemResponse(CartItem cartItem) {
        Product product = cartItem.getProduct();
        return CartDTO.ItemResponse.builder()
                .productId(product.getId())
                .title(product.getTitle())
                .price(product.getPrice())
                .quantity(cartItem.getQuantity())
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())))
                .build();

    }

    public CartDTO.CartResponse toCartResponse(Cart cart) {
        List<CartDTO.ItemResponse> items = cart.getItems().stream()
                .map(this::toItemResponse)
                .collect(Collectors.toList());

        BigDecimal totalAmount = items.stream()
                .map(CartDTO.ItemResponse::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return CartDTO.CartResponse.builder()
                .cartId(cart.getId())
                .userId(cart.getUser().getId())
                .userName(cart.getUser().getName())
                .items(items)
                .totalAmount(totalAmount)
                .build();
    }

    // DTO -> 엔티티
    public CartItem toCartItem(Cart cart, Product product, CartDTO.ItemRequest request) {
        return CartItem.builder()
                .cart(cart)
                .product(product)
                .quantity(request.getQuantity())
                .build();
    }
}
