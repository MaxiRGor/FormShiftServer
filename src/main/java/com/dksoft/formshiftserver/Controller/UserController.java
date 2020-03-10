package com.dksoft.formshiftserver.Controller;

import com.dksoft.formshiftserver.Model.User;
import com.dksoft.formshiftserver.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/get/{id}")
    public @ResponseBody
    String getUser(@PathVariable("id") int id) {
        User user = userRepository.findAllById(id).get(0);
        String userInfo = getUserInfo(user);
        System.out.println(userInfo);
        return userInfo;
    }

    @GetMapping("/create/{facebook_id}/{email}/{name}")
    @ResponseBody
    public User CreateUser (@PathVariable String facebook_id, @PathVariable String email, @PathVariable String name) {
        User user = new User(facebook_id, email, name);
        System.out.println(getUserInfo(user));
        userRepository.save(user);
        return user;
    }

    private String getUserInfo(User user){
        return  user.id + " " + user.name + " " + user.email;
    }

}
