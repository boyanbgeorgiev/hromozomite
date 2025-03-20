package org.example.service;

import org.example.exception.UserNotFoundException;
import org.example.persistence.model.SessionEntity;
import org.example.persistence.model.UserAccountEntity;
import org.example.persistence.repository.SessionRepository;
import org.example.persistence.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private UserAccountRepository userAccountRepository;

    public SessionEntity createSession(){
        SessionEntity sessionEntity = new SessionEntity();
        UUID uuid = UUID.randomUUID();
        sessionEntity.setSessionString(uuid.toString());
        sessionEntity.setExpirationDateInMillis(System.currentTimeMillis() + 15 * 60 * 1000);
        return sessionEntity;
    }

    public UserAccountEntity getUserFromSessionString(String sessionString) throws UserNotFoundException {
        return userAccountRepository.findBySession_SessionString(sessionString).orElseThrow( () -> new UserNotFoundException("User not found!"));
    }
}
