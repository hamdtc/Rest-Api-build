package com.example.RestApiBuild;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MyController {

    //this is local data base for the time being
    Map<Integer,User> users=new HashMap<>();

    @GetMapping("/get_users") // getting the user list not the specific
    public List<User> getUsers(){
        List<User> listOfUsers=new ArrayList<>();
        for(User u:users.values())
            listOfUsers.add(u);

        return listOfUsers;
    }
    @GetMapping("/get_user_path/{id}") // specifically getting one user
    public User getUserbyPath(@PathVariable("id") int id){
        return users.get(id);
    }

    //creating new users by giving input
    @PostMapping("/add_user")
    public void createNewUser(@RequestParam("id") int id,
                              @RequestParam("name") String name,
                              @RequestParam("country") String country,
                              @RequestParam("age") int age){
        User user=new User(id,name,country,age);
        users.put(id,user);
    }
    @PostMapping("/add_user_bybody") // add new user by giving JSON after
    public void addUserBybody(@RequestBody(required = true) User u){
        users.put(u.getId(),u);
    }

    //delete a specific id user otherwise use map traverse
    @DeleteMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable("id") int id){
        users.remove(id);


    }

    //for update user need id or u can change with name etc..
    @PutMapping("/update_user/{id}")
    public void updateUser(@PathVariable("id") int id,@RequestBody() User user){
        users.put(id,user);

    }

}
