package com.example.WebsiteBanNhacCu_DoAn.Services;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Profile;
import com.example.WebsiteBanNhacCu_DoAn.Entities.User;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.IUserRepository;
import com.example.WebsiteBanNhacCu_DoAn.Repositories.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private IUserRepository userRepository;

    public Profile getProfileByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        Profile profile = profileRepository.findByUser(user.getId()).get();
        return profile ;
    }
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfileById(Long id) {
        return profileRepository.findById(id).get();
    }

    public Profile saveProfile(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        return profileRepository.save(profile);
    }
    public Profile updateProfile(Profile profile) {
        return profileRepository.save(profile);
    }
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }
}
