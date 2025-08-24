package com.ccs.Models;

import jakarta.persistence.*;
        import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Providers")
public class Provider extends User {
        public Provider(String username, String password, String email, String phone) {
        super();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setPhone(phone);
        setRole(Role.ROLE_PROVIDER);
    }
}

