package com.FirstApp.JournalApp.controller;

import com.FirstApp.JournalApp.Entity.JournalEntry;
import com.FirstApp.JournalApp.Entity.Users;
import com.FirstApp.JournalApp.repository.UsersRepository;
import com.FirstApp.JournalApp.services.JournalEntryServices;
import com.FirstApp.JournalApp.services.UsersServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Users")

public class UsersController {

    @Autowired
    private UsersServices usersServices;

    @GetMapping
    public List<Users> getAll(){
       return usersServices.getAll();
    }


    @PostMapping
    public Users saveEntry(@RequestBody Users myEntry){
        usersServices.saveEntry(myEntry);
        return myEntry;
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Users>  findById(@PathVariable ObjectId id){
        // here we used a Response Entity to give us our needed response Http Status code
        Optional<Users>users= usersServices.getById(id);
        if(users.isPresent()){
            return new ResponseEntity<>(users.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId) {
        usersServices.deleteUserById(myId);
        return true;

    }
@PutMapping("/userName/{userName}")
public Users updateUsersByUserName(
        @PathVariable String userName,
        @RequestBody Users newEntry){

    Users old = usersServices.getByUserName(userName);
    System.out.println("Incoming username: " + userName);
    System.out.println("Old user: " + old);
    System.out.println("New entry: " + newEntry);
    if (old == null) {
        throw new RuntimeException("User not found");
    }

    if (newEntry.getUserName() != null && !newEntry.getUserName().isBlank()) {
        old.setUserName(newEntry.getUserName());
    }

    if (newEntry.getPassword() != null && !newEntry.getPassword().isBlank()) {
        old.setPassword(newEntry.getPassword());
    }

    usersServices.saveEntry(old);
    return old;
}
}

