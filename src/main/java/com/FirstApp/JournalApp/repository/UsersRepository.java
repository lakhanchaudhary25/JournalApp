package com.FirstApp.JournalApp.repository;

import com.FirstApp.JournalApp.Entity.JournalEntry;
import com.FirstApp.JournalApp.Entity.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, ObjectId> {

     Users findByUserName(String username);
     Users findByJournalEntryListContains(JournalEntry journalEntry);
}
