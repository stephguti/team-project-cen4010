package com.bookstore.bookstore_api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null;
    }

    public void updateUser(String username, User updatedUser) {
        User existingUser = userRepository.findByUsername(username);

        if(existingUser == null) {
            throw new IllegalArgumentException("ERROR! User not found");
        }

        if(updatedUser.getFirstName() != null) {
            existingUser.setFirstName(updatedUser.getFirstName());
        }

        if(updatedUser.getLastName() != null) {
            existingUser.setLastName(updatedUser.getLastName());
        }

        if(updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        if(updatedUser.getUserAddress() != null) {
            existingUser.setUserAddress(updatedUser.getUserAddress());
        }

        userRepository.save(existingUser);
    }

    
}