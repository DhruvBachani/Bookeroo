package com.rmit.sept.bk_loginservices.services;

import com.rmit.sept.bk_loginservices.Repositories.UserRepository;
import com.rmit.sept.bk_loginservices.exceptions.UsernameAlreadyExistsException;
import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.payload.UserID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        /*  newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            Username has to be unique (exception)
            Make sure that password and confirmPassword match
            We don't persist or show the confirmPassword
        */
        newUser.setFullName(newUser.getFullName());
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setAddress(newUser.getAddress());
        newUser.setUserType(newUser.getUserType());

        // Make sure that password and confirmPassword match
        // We don't persist or show the confirmPassword
        newUser.setConfirmPassword("");


        // Setting the user to get approval for admin if they are a publisher or shop publisher
        if (newUser.getUserType().equals("Publisher") || newUser.getUserType().equals("Shop owner")) {
            newUser.setApproved(false);
        } else {
            newUser.setApproved(true);
        }

        // ABN has to be unique (exception)
        // Phone number has to be unique (exception)
        newUser.setUsername(newUser.getUsername());
        userRepository.findAll().forEach(user -> {
            if (user.getUsername().equals(newUser.getUsername())) {
                throw new UsernameAlreadyExistsException("Username '" + newUser.getUsername() + "' already exists");
            }
        });
        newUser.setPhoneNumber(newUser.getPhoneNumber());
        userRepository.findAll().forEach(user -> {
            if (user.getPhoneNumber() != null && newUser.getPhoneNumber() != null && user.getPhoneNumber().equals(newUser.getPhoneNumber())) {
                throw new UsernameAlreadyExistsException("Phone Number '" + newUser.getPhoneNumber() + "' already exists");
            }
        });
        newUser.setAbn_number(newUser.getAbnNumber());
        userRepository.findAll().forEach(user -> {
            if (user.getAbnNumber() != null && newUser.getAbnNumber() != null && user.getAbnNumber().equals(newUser.getAbnNumber())) {
                throw new UsernameAlreadyExistsException("ABN Number '" + newUser.getAbnNumber() + "' already exists");
            }
        });

        try {
            return userRepository.save(newUser);
        }catch (Exception e) {
            throw new UsernameAlreadyExistsException("Something went wrong");
        }
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }


    // Returns a specific user by their username(email)
    public User retrieveUserUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    public User retreiveUserbyUserId(Long userId){ return userRepository.getById(userId);}

    // Returns a list of all the users that are not the admins
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if ((!user.getUserType().equals("Admin")) && (!user.getBanned())) {
                allUsers.add(user);
            }
        });
        return allUsers;

    }

    // Returns a list of all the publishers and shop owners that have not been approved yet
    public List<User> getAllUnapprovedUsers() {
        List<User> unapprovedUsers = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            if (!user.getApproved() && !user.getBanned()) {
                unapprovedUsers.add(user);
            }
        });
        return unapprovedUsers;
    }

    // Changes the publishers/shop owners account approval to be true, so the publishers/shop owners can log in
    public boolean setApproval(@Valid @RequestBody UserID id) {
        User user = userRepository.getById(id.getId());
        if (user == null) {
            return false;
        } else if (!user.getApproved()) {
            user.setApproved(true);
            updateUser(user);
        }
        return true;
    }

    // Changes the approval to false, in the case the admin made a mistake
    public boolean setUnapproval(long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return false;
        } else if (user.getApproved()) {
            user.setApproved(false);
            updateUser(user);
        }
        return true;
    }

    // Changes the ban boolean of a user to true, so users cannot log in anymore
    public boolean banUser(@Valid UserID id) {
        User user = userRepository.getById(id.getId());
        if (user == null) {
            return false;
        } else {
            user.setBanned(true);
            updateUser(user);
        }
        return true;
    }

    // Changes the ban boolean of a user to false in the case the admin makes a mistake
    public boolean unbanUser(long id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return false;
        } else {
            user.setBanned(false);
            updateUser(user);
        }
        return true;
    }



}
