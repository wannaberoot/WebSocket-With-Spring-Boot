package com.example.websocketwithspringboot;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "admin-api")
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/listUsers")
    public Iterable<User> listUsers(){
        return userRepository.findAll();
    }

    @PostMapping("/createUser")
    public void createUser(@RequestParam String userName, @RequestParam String phoneNumber) {
        User user = new User();
        user.setUserName(userName);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
    }

    @DeleteMapping("/deleteUserById")
    public void deleteUserById(@RequestParam long id) {
        userRepository.deleteById(id);
    }
}
