package com.example.WebsiteBanNhacCu_DoAn.Controllers;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Product;
import com.example.WebsiteBanNhacCu_DoAn.Entities.Item;
import com.example.WebsiteBanNhacCu_DoAn.Services.ProductService;
import com.example.WebsiteBanNhacCu_DoAn.Services.CartService;
import com.example.WebsiteBanNhacCu_DoAn.Services.CategoryService;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;
    @GetMapping
    public String showAllProducts(@NotNull Model model,
                                  @RequestParam(defaultValue = "0")
                                  Integer pageNo,
                                  @RequestParam(defaultValue = "20")
                                  Integer pageSize,
                                  @RequestParam(defaultValue = "id")
                                  String sortBy) {
        model.addAttribute("products", productService.getAllProducts(pageNo,
                pageSize, sortBy));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                productService.getAllProducts(pageNo, pageSize, sortBy).size() / pageSize);
        model.addAttribute("categories",
                categoryService.getAllCategories());
        return "product/list";
    }
    @GetMapping("/add")
    public String addProductForm(@NotNull Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories",
                categoryService.getAllCategories());
        return "product/add";
    }
    @PostMapping("/add")
    public String addProduct(
            @Valid @ModelAttribute("product") Product product,
            @NotNull BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories",
                    categoryService.getAllCategories());
            return "product/add";
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(HttpSession session,
                            @RequestParam long id,
                            @RequestParam String name,
                            @RequestParam int price,
                            @RequestParam String description,
                            @RequestParam(defaultValue = "1") int
                                    quantity) {
        var cart = cartService.getCart(session);
        cart.addItems(new Item(id, name, price, description, quantity));
        cartService.updateCart(session, cart);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@NotNull Model model, @PathVariable long id)
    {
        var product = productService.getProductById(id);
        model.addAttribute("product", product.orElseThrow(() -> new
                IllegalArgumentException("Product not found")));
        model.addAttribute("categories",
                categoryService.getAllCategories());
        return "product/edit";
    }
    @PostMapping("/edit")
    public String editProduct(@Valid @ModelAttribute("product") Product product,
                              @NotNull BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories",
                    categoryService.getAllCategories());
            return "product/edit";
        }
        productService.updateProduct(product);
        return "redirect:/products";
    }


    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.getProductById(id)
                .ifPresentOrElse(
                        product -> productService.deleteProductById(id),
                        () -> { throw new IllegalArgumentException("Product not found"); });
        return "redirect:/products";
    }
    @GetMapping("/search")
    public String searchProduct(
            @NotNull Model model,
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "20") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
//        model.addAttribute("products", productService.searchProduct(keyword));
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages",
                productService
                        .getAllProducts(pageNo, pageSize, sortBy)
                        .size() / pageSize);
        model.addAttribute("categories",
                categoryService.getAllCategories());
        return "product/list";
    }
}

