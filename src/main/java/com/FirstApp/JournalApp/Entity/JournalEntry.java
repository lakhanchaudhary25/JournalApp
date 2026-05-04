package com.FirstApp.JournalApp.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
public class JournalEntry {

    // 🔹 Fields (always on top)
    @Id
    private ObjectId id;

    private String title;

    private String content;
    private LocalDateTime date;

    // 🔹 Default Constructor (required)
    public JournalEntry() {}

    // 🔹 Getters & Setters (grouped properly)

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {   // ✅ FIXED naming
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {  // ✅ FIXED naming
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}