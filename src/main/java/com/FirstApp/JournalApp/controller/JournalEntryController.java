package com.FirstApp.JournalApp.controller;

import com.FirstApp.JournalApp.Entity.JournalEntry;
import com.FirstApp.JournalApp.Entity.Users;
import com.FirstApp.JournalApp.JournalApplication;
import com.FirstApp.JournalApp.services.JournalEntryServices;
import com.FirstApp.JournalApp.services.UsersServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/Journal")

public class JournalEntryController {

    @Autowired
    private JournalEntryServices journalEntryServices;
    @Autowired
    private UsersServices usersServices;



    @GetMapping("/userName/{userName}")
    public List<JournalEntry> getAllJournalEntryByUserName(@PathVariable String userName ){
         Users user = usersServices.getByUserName(userName.trim());
         if(user==null){
             throw new RuntimeException("not Found");
         }
         List<JournalEntry> all = user.getJournalEntryList();
         if(all!=null){
             return all;
         }

         return new ArrayList<>();
    }


    @PostMapping("/userName/{userName}")
    public JournalEntry saveEntryByUserName(@PathVariable String userName, @RequestBody JournalEntry myEntry){
        Users user = usersServices.getByUserName(userName.trim());
        if(user==null){
            throw new RuntimeException("user not found");
        }
        JournalEntry savedEntry = journalEntryServices.saveEntry(myEntry);// save entry
       user.getJournalEntryList().add(savedEntry);// save entry in the user
        usersServices.saveEntry(user);// save the user
        return savedEntry;
    }



    @GetMapping("/id/{id}")
    public ResponseEntity<JournalEntry>  findById(@PathVariable ObjectId id){
        // here we used a Response Entity to give us our needed response Http Status code
        Optional<JournalEntry>journalEntry= journalEntryServices.getById(id);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @DeleteMapping("/id/{id}")
    public String deleteEntry(@PathVariable String id){

        ObjectId objectId = new ObjectId(id);

        // 1. find entry
        JournalEntry entry = journalEntryServices.getById(objectId).orElse(null);

        if (entry == null) {
            throw new RuntimeException("Entry not found");
        }
        Users user = usersServices.findByJournalEntry(entry);

        if (user != null && user.getJournalEntryList() != null) {
            user.getJournalEntryList().remove(entry);
            usersServices.saveEntry(user);
        }
        journalEntryServices.deleteEntry(objectId);

        return "Deleted successfully";
    }



    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
             JournalEntry old=journalEntryServices.getById(id).orElse(null);
        if (old == null) {
            throw new RuntimeException("Entry not found");
        }

        if (newEntry.getTitle() != null && !newEntry.getTitle().isBlank()) {
            old.setTitle(newEntry.getTitle());
        }

        if (newEntry.getContent() != null && !newEntry.getContent().isBlank()   ) {
            old.setContent(newEntry.getContent());
        }

             journalEntryServices.saveEntry(old);
               return old;
    }

}

