package com.kftech.ecommercebackend.service;

import com.kftech.ecommercebackend.api.model.RegistrationBody;
import com.kftech.ecommercebackend.dao.LocalUserDAO;
import com.kftech.ecommercebackend.exception.UserAlreadyExistException;
import com.kftech.ecommercebackend.model.LocalUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private LocalUserDAO localUserDAO;

    @Autowired
    public UserService(LocalUserDAO localUserDAO) {
        this.localUserDAO = localUserDAO;
    }

    public LocalUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistException {

        if (localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        LocalUser user = new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        //TODO: Encrypte password!!
        user.setPassword(registrationBody.getPassword());
        localUserDAO.save(user);
        return user;
    }

}
