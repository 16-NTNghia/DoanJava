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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @GetMapping("")
    public String showAllProducts(Model model) {
        model.addAttribute("categories",
                categoryService.getCategories());
        return "product/index";
    }
    @GetMapping("/list")
    public String listProductForm(@NotNull Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "9", required = false) int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productsPage = productService.getAllProducts(pageable);
        model.addAttribute("products", productsPage);
        return "product/list";
    }

    @GetMapping("/{id}")
    public String editProductForm(@NotNull Model model, @PathVariable long id)
    {
        var product = productService.getProductById(id);
        model.addAttribute("product", product.orElseThrow(() -> new
                IllegalArgumentException("Product not found")));
        model.addAttribute("categories",
                categoryService.getCategories());
        return "product/detail";
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
                    categoryService.getCategories());
            return "product/edit";
        }
        productService.updateProduct(product);
        return "redirect:/products";
    }
//    @GetMapping("/search")
//    public String searchProduct(
//            @NotNull Model model,
//            @RequestParam String keyword) {
////        model.addAttribute("products", productService.searchProduct(keyword));
//        model.addAttribute("totalPages",
//                productService
//                        .getProducts());
//        model.addAttribute("categories",
//                categoryService.getCategories());
//        return "product/list";
//    }
}

