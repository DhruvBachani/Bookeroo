package com.rmit.sept.bk_loginservices;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import com.rmit.sept.bk_loginservices.Repositories.UserRepository;
import com.rmit.sept.bk_loginservices.model.User;
import com.rmit.sept.bk_loginservices.payload.UserID;
import com.rmit.sept.bk_loginservices.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.mockito.BDDMockito.*;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private User publisher1 = new User();
    private User publisher = new User();
    private User shopOwner = new User();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        // Filling our 'database' with mock data
        long id = 1;
        publisher1.setId(id);
        publisher1.setFullName("Finn");
        publisher1.setUserType("Publisher");
        publisher1.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        publisher1.setUsername("h@gmail.com");
        publisher1.setAbn_number("12345612345");
        publisher1.setAddress("12 Adventure Street");
        publisher1.setShopName("The Land of Ooks");
        publisher1.setPhoneNumber("0412345123");
        publisher1.setApproved(false);

        long id2 = 2;
        shopOwner.setId(id2);
        shopOwner.setFullName("Steve Irwin");
        shopOwner.setUserType("Shop owner");
        shopOwner.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        shopOwner.setUsername("crikey@gmail.com");
        shopOwner.setAbn_number("12345678901");
        shopOwner.setAddress("42 Crocodile Street");
        shopOwner.setShopName("Australian Wildlife Bookstore");
        shopOwner.setPhoneNumber("0412123123");
        shopOwner.setApproved(true);

        long id3 = 3;
        publisher.setId(id3);
        publisher.setFullName("Jesus Christ");
        publisher.setUserType("Publisher");
        publisher.setPassword(bCryptPasswordEncoder.encode("asdasdasd"));
        publisher.setUsername("jesus@gmail.com");
        publisher.setAbn_number("77777777777");
        publisher.setAddress("77 Heaven Street");
        publisher.setShopName("We only sell the bible");
        publisher.setPhoneNumber("04777777");
        publisher.setUsername("jesus@gmail.com");
        publisher.setApproved(false);

        // Creating mock dependencies to not rely on external databases
        Mockito.when(userRepository.save(any(User.class))).thenReturn(new User());
        Mockito.when(userRepository.findByUsername(publisher1.getUsername())).thenReturn(publisher1);
        Mockito.when(userRepository.getById(publisher1.getId())).thenReturn(publisher1);
        Mockito.when(userRepository.getById(shopOwner.getId())).thenReturn(shopOwner);


    }

    @Test
    public void getUser_ReturnUser_IfValidUsername()
    {
        String customerUsername = "h@gmail.com";
        User found = userService.retrieveUserUsername(customerUsername);

        assertEquals(found.getUsername(), customerUsername);
    }


    @Test
    public void getUser_ReturnNull_IfInvalidUsername()
    {
        String customerUsername = "idontexist@gmail.com";
        User found = userService.retrieveUserUsername(customerUsername);

        assertNull(found);
    }

    @Test
    public void getUser_ReturnUser_IfValidUserID()
    {
        long customerID = 1;
        User found = userService.retreiveUserbyUserId(customerID);

        assertEquals((long)found.getId(), customerID);
    }


    @Test
    public void getUser_ReturnNull_IfInvalidUserID()
    {
        long customerID = 9999;
        User found = userService.retreiveUserbyUserId(customerID);

        assertNull(found);
    }

    @Test
    public void getAllUnapprovedUser_ReturnList_IfValidUsersInDatabase()
    {
        List<User> list = new ArrayList<>();
        list.add(publisher);
        list.add(publisher1);
        list.add(shopOwner);

        // Stub
        Mockito.when(userRepository.findAll()).thenReturn(list);

        List<User> actual = userService.getAllUnapprovedUsers();

        assertEquals(actual.size(), 2);
        assertFalse(actual.stream().anyMatch(e ->  "crikey@gmail.com".equals(e.getUsername())));
        assertTrue(actual.stream().anyMatch(e ->  "jesus@gmail.com".equals(e.getUsername())));
        assertTrue(actual.stream().anyMatch(e ->  "h@gmail.com".equals(e.getUsername())));
    }

    @Test
    public void approveUser_ReturnTrue_IfApproveValidUser()
    {
        UserID id = new UserID();
        long customerID = 1;
        id.setId(customerID);

        assertTrue(userService.setApproval(id));
        assertEquals(userRepository.getById(customerID).getApproved(), true);

    }

    @Test
    public void approveUser_ReturnFalse_IfApproveNonExistingUser()
    {
        UserID id = new UserID();
        long customerID = 9999;
        id.setId(customerID);

        assertFalse(userService.setApproval(id));
    }

    @Test
    public void unapprovedUser_ReturnTrue_IfUnapprovedValidUser()
    {
        long customerID = 2;

        assertTrue(userService.setUnapproval(customerID));
        assertEquals(userRepository.getById(customerID).getApproved(), false);

    }

    @Test
    public void unapprovedUser_ReturnFalse_IfUnapprovedNonExistingUser()
    {
        UserID id = new UserID();
        long customerID = 9999;
        id.setId(customerID);

        assertFalse(userService.setApproval(id));
    }

    @Test
    public void banUser_ReturnTrue_IfBanValidUser()
    {
        UserID id = new UserID();
        long customerID = 1;
        id.setId(customerID);

        assertTrue(userService.banUser(id));
        assertEquals(userRepository.getById(customerID).getBanned(), true);
    }

    @Test
    public void banUser_ReturnFalse_IfBanNonExistingUser()
    {
        UserID id = new UserID();
        long customerID = 9999;
        id.setId(customerID);

        assertFalse(userService.banUser(id));
    }

    @Test
    public void unbanUser_ReturnTrue_IfUnbanValidUser()
    {
        long customerID = 1;

        assertTrue(userService.unbanUser(customerID));
        assertEquals(userRepository.getById(customerID).getBanned(), false);

    }

    @Test
    public void unbanUser_ReturnFalse_IfUnbanNonExistingUser()
    {
        long customerID = 9999;

        assertFalse(userService.unbanUser(customerID));
    }

    @Test
    public void saveUser_ReturnUser_IfDetailsAreValid()
    {
        User actual = userService.saveUser(publisher1);

        assertNotNull(actual);
    }

    @Test
    public void updateUser_ReturnUser_IfDetailsAreValid()
    {
        publisher1.setShopName("The Land of Books");

        userService.updateUser(publisher1);

        assertEquals(publisher1.getShopName(), "The Land of Books");
    }

}
