package org.example.service;

import org.example.controller.model.UserCredentialsDTO;
import org.example.persistence.model.SessionEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.repository.SessionRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public void signup(UserCredentialsDTO userCredentialsDTO) {
        UserAccountEntity userAccountEntity = new UserAccountEntity();
        userAccountEntity.setUsername(userAccountEntity.getUsername());
        userAccountEntity.setPassword(userAccountEntity.getPassword());
        userAccountEntity.setEmail(userAccountEntity.getEmail());
        userAccountRepository.save(userAccountEntity);
    }

    public void login(UserCredentialsDTO userCredentialsDTO){
        String email = userCredentialsDTO.getEmail();
        String password = userCredentialsDTO.getPassword();
        UserAccountEntity userAccountEntity = userAccountRepository.findByEmailAndPassword(email, password).orElse(null);

        if (userAccountEntity == null) {
            throw new RuntimeException("No user found with the provided credentials.");
        }

    }
    public void logout(String sessionString){
        SessionEntity sessionEntity = sessionRepository.findBySessionString(sessionString).orElse(null);
        if (sessionEntity != null) {
            sessionRepository.delete(sessionEntity);
        }
    }
}
