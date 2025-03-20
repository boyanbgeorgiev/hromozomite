package org.example.persistence.repository;

import org.example.persistence.model.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAccountRepository extends JpaRepository<UserAccountEntity, Long> {
    Optional<UserAccountEntity> findByEmailAndPassword(String username, String password);
}

