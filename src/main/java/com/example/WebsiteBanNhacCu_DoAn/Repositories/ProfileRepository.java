package com.example.WebsiteBanNhacCu_DoAn.Repositories;

import com.example.WebsiteBanNhacCu_DoAn.Entities.Profile;
import com.example.WebsiteBanNhacCu_DoAn.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p WHERE p.user.id = ?1")
    Optional<Profile> findByUser(Long userId);

}