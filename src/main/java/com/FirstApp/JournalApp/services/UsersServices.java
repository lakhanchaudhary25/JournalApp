package com.FirstApp.JournalApp.services;

import com.FirstApp.JournalApp.Entity.JournalEntry;
import com.FirstApp.JournalApp.Entity.Users;
import com.FirstApp.JournalApp.repository.JournalEntryRepository;
import com.FirstApp.JournalApp.repository.UsersRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServices {

    @Autowired
    private UsersRepository usersRepository;

    public void saveEntry(Users users){
        usersRepository.save(users);
    }

    public List<Users> getAll(){
        return usersRepository.findAll();
    }
    public Optional<Users> getById(ObjectId myId){
        return usersRepository.findById(myId);
    }
    public void deleteUserById(ObjectId id){
        usersRepository.deleteById(id);

    }
    public Users getByUserName(String username){
        return usersRepository.findByUserName(username);
    }
    public Users findByJournalEntry(JournalEntry entry){
        return usersRepository.findByJournalEntryListContains(entry);
    }

}
