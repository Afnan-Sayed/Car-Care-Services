package com.ccs.Repositories;

import com.ccs.Models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long>
{
    Optional<PasswordResetToken> findByToken(String token);
}