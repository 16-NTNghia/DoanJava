package com.example.WebsiteBanNhacCu_DoAn.Repositories;

import com.example.WebsiteBanNhacCu_DoAn.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    User findByEmail(String email);
}