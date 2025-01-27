package com.example.WebsiteBanNhacCu_DoAn.Controllers;

import com.example.WebsiteBanNhacCu_DoAn.Entities.User;
import com.example.WebsiteBanNhacCu_DoAn.Services.ProfileService;
import com.example.WebsiteBanNhacCu_DoAn.Services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private ProfileService profileService;
    private final UserService userService;
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @GetMapping("/register")
    public String register(@NotNull Model model) {
        model.addAttribute("user", new User());
        return "User/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           @NotNull BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            return "User/register";
        }
        userService.save(user);
        userService.setDefaultRole(user.getUsername());
        profileService.saveProfile(user);
        return "redirect:/login";
    }

}