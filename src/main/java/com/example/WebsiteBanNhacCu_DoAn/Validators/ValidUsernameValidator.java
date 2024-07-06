package com.example.WebsiteBanNhacCu_DoAn.Validators;

import com.example.WebsiteBanNhacCu_DoAn.Repositories.IUserRepository;
import com.example.WebsiteBanNhacCu_DoAn.Validators.Annotations.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Component
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {

    @Autowired
    private IUserRepository userService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (userService == null)
            return true;
        return userService.findByUsername(username).isEmpty();
    }
}
