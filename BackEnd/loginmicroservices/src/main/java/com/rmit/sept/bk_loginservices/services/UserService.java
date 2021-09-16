package com.rmit.sept.bk_loginservices.services;




import com.rmit.sept.bk_loginservices.Repositories.UserRepository;
import com.rmit.sept.bk_loginservices.exceptions.UsernameAlreadyExistsException;
import com.rmit.sept.bk_loginservices.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

      /*  newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        //Username has to be unique (exception)
        // Make sure that password and confirmPassword match
        // We don't persist or show the confirmPassword
        return userRepository.save(newUser);
       */
        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            newUser.setAddress(newUser.getAddress());
            newUser.setPhoneNumber(newUser.getPhoneNumber());
            newUser.setAbn_number(newUser.getAbnNumber());
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
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }

    public User retreiveUserbyUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    public boolean setApproval(String username) {

        User user = retreiveUserbyUsername(username);
        if (user == null) {
            return false;
        } else if (user.getApproved() == false) {
            user.setApproved(true);
        }
        return true;
    }



}
