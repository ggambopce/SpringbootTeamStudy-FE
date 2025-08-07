package com.springboot.studyfe.data.entity;

import com.google.gson.annotations.SerializedName;

public class Board {

    @SerializedName("post_id")
    private Long postId;

    @SerializedName("user_id")
    private Long userId;

    @SerializedName("post_title")
    private String postTitle;

    @SerializedName("post_content")
    private String postContent;

    @SerializedName("created_at")
    private String createdAt; // LocalDateTime은 문자열로 받아서 처리

    public Board() {}

    public Board(Long userId, String postTitle, String postContent) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
    }

    // Getter & Setter

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
