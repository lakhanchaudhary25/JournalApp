package com.FirstApp.JournalApp.services;

import com.FirstApp.JournalApp.Entity.JournalEntry;
import com.FirstApp.JournalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry){
        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> getById(ObjectId myId){
        return journalEntryRepository.findById(myId);
    }
    public void deleteEntry(ObjectId id){
        journalEntryRepository.deleteById(id);
    }


}
