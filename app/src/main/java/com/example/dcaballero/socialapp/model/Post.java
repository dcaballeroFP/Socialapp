package com.example.dcaballero.socialapp.model;

import com.google.firebase.database.Exclude;
import java.util.HashMap;
import java.util.Map;

public class Post {
    public String uid;
    public String author;
    public String authorPhotoUrl;
    public String content;
    public String mediaUrl;
    public String mediaType;
    public Map<String, Boolean> likes = new HashMap<>();

    public Post() {}

    public Post(String uid, String author, String authorPhotoUrl, String content, String mediaUrl, String mediaType) {
        this.uid = uid;
        this.author = author;
        this.authorPhotoUrl = authorPhotoUrl;
        this.content = content;
        this.mediaUrl = mediaUrl;
        this.mediaType = mediaType;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("author", author);
        result.put("authorPhotoUrl", authorPhotoUrl);
        result.put("content", content);
        result.put("mediaUrl", mediaUrl);
        result.put("mediaType", mediaType);
        result.put("likes", likes);

        return result;
    }
}
