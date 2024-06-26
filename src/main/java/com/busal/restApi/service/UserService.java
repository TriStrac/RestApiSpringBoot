package com.busal.restApi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.busal.restApi.api.controller.model.User;

@Service
public class UserService{
    
    private List<User> userList;

    public UserService(){
        userList = new ArrayList<>();

        User user1 = new User(1, "Frans", 20, "frans@gmail.com", "frans", "frans123");
        User user2 = new User(2, "Alice", 25, "alice@gmail.com", "alice", "alice123");
        User user3 = new User(3, "Bob", 30, "bob@gmail.com", "bob", "bob123");
        User user4 = new User(4, "Cathy", 22, "cathy@gmail.com", "cathy", "cathy123");
        User user5 = new User(5, "David", 28, "david@gmail.com", "david", "david123");

        userList.addAll(Arrays.asList(user1,user2,user3,user4,user5));
    }

    public Optional<User> getUser(Integer id){
        Optional optional = Optional.empty();
        for(User user: userList){
            if(id == user.getId()){
                optional = Optional.of(user);
                return optional;
            }
        }
        return optional;
    }

    public User getUserByUsername(String username) {
        return userList.stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst()
                .orElse(null);
    }
}