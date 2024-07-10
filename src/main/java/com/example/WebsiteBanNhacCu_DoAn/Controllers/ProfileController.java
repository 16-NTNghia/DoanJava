package com.example.WebsiteBanNhacCu_DoAn.Controllers;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Profile;
import com.example.WebsiteBanNhacCu_DoAn.Entities.User;
import com.example.WebsiteBanNhacCu_DoAn.Services.ProfileService;
import com.example.WebsiteBanNhacCu_DoAn.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    private final String UPLOAD_DIR = "uploads/";
    @GetMapping
    public String getAllProfiles(Model model, User user) {
        model.addAttribute("profiles",user.getId());
        return "profiles/list";
    }

    @GetMapping("/new")
    public String createProfileForm(Model model) {
        model.addAttribute("profile", new Profile());
        return "profiles/create";
    }

//    @PostMapping
//    public String createProfile(@ModelAttribute("profile") Profile profile) {
//        profileService.saveProfile(profile);
//        return "redirect:/profiles";
//    }

    @GetMapping("/edit/{id}")
    public String editProfileForm(@PathVariable Long id, Model model) {
        model.addAttribute("profile", profileService.getProfileById(id));
        return "profiles/edit";
    }

    @PostMapping("/{id}")
    public String updateProfile(@PathVariable Long id, @ModelAttribute("profile") Profile profile) {
        profile.setId(id);
        profileService.updateProfile(profile);
        return "redirect:/profiles";
    }

    @GetMapping("/delete/{id}")
    public String deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return "redirect:/profiles";
    }


    private void savePhoto(Profile profile, MultipartFile photoFile) throws IOException {
        if (photoFile != null && !photoFile.isEmpty()) {
            String fileName = photoFile.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);
            photoFile.transferTo(path);
            profile.setPhoto(fileName);
        }
    }
}
