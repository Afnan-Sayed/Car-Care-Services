package com.ccs.Services;

import com.ccs.Models.PasswordResetToken;
import com.ccs.Models.User;
import com.ccs.Repositories.PasswordResetTokenRepository;
import com.ccs.Repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordResetService
{
    private final UserRepository userRepository;
    private final PasswordResetTokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordResetService(UserRepository userRepository,
                                PasswordResetTokenRepository tokenRepository,
                                PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //generate and send reset token
    public void forgotPassword(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            System.out.println("User NOT found with email: " + email);
            return new RuntimeException("User not found");
        });

        System.out.println("User found: ID=" + user.getId() + ", Email=" + user.getEmail());

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpiryDate(LocalDateTime.now().plusMinutes(10));
        tokenRepository.save(resetToken);

        System.out.println("token for " +email + " = " + token);
    }

    //verify token
    public boolean verifyToken(String token, String email)
    {
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> {
                    System.out.println("Token expired");
                    return new RuntimeException("Token expired");
                });


        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now()))
            { throw new RuntimeException("Token expired");}

        return resetToken.getUser().getEmail().equals(email);
    }

    //reset password
    public void resetPassword(String email, String token, String newPassword)
    {
        if (!verifyToken(token, email))
            { throw new RuntimeException("Invalid token or email");}

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        //delete token after use
        tokenRepository.deleteById(tokenRepository.findByToken(token).get().getId());
    }
}
