package com.example.WebsiteBanNhacCu_DoAn.Services;

import com.example.WebsiteBanNhacCu_DoAn.Entities.*;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IProductRepository;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IInvoiceRepository;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IItemInvoiceRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@SessionScope
public class CartService {
    private List<Cart> cartItems = new ArrayList<>();
    @Autowired
    private IProductRepository productRepository;

    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findByIdAndIsDeleteFalse(productId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        cartItems.add(new Cart(product, quantity));
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Cart cartItem : cartItems) {
            total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return total;
    }

    public List<Cart> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public void clearCart() {
        cartItems.clear();
    }
}

