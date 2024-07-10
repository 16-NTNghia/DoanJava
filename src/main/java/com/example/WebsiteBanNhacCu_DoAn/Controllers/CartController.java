package com.example.WebsiteBanNhacCu_DoAn.Controllers;

import com.example.WebsiteBanNhacCu_DoAn.Services.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    @Autowired
    private CartService cartService;
    @GetMapping("")
    public String showCart(Model model) {
        model.addAttribute("cartItems", cartService.getCartItems());
        model.addAttribute("total", cartService.calculateTotal());
        return "product/cart";
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity) {
        cartService.addToCart(productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId) {
        cartService.removeFromCart(productId);
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "redirect:/cart";
    }
//    @GetMapping("/removeFromCart/{id}")
//    public String removeFromCart(HttpSession session,
//                                 @PathVariable Long id) {
//        var cart = cartService.getCart(session);
//        cart.removeItems(id);
//        return "redirect:/cart";
//    }
//    @GetMapping("/updateCart/{id}/{quantity}")
//    public String updateCart(HttpSession session,
//                             @PathVariable int id,
//                             @PathVariable int quantity) {
//        var cart = cartService.getCart(session);
//        cart.updateItems(id, quantity);
//        return "book/cart";
//    }
//    @GetMapping("/clearCart")
//    public String clearCart(HttpSession session) {
//        cartService.removeCart(session);
//        return "redirect:/cart ";
//    }
//    @GetMapping("/checkout")
//    public String checkout(HttpSession session) {
//        cartService.saveCart(session);
//        return "redirect:/cart";
//    }
    @GetMapping("/increase/{productId}")
    public String increaseQuantity(@PathVariable Long productId) {
        cartService.increaseQuantity(productId);
        return "redirect:/cart";
    }

    @GetMapping("/decrease/{productId}")
    public String decreaseQuantity(@PathVariable Long productId) {
        cartService.decreaseQuantity(productId);
        return "redirect:/cart";
    }

}

