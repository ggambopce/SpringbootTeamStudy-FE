package com.springboot.studyfe.data.dto;

public class PostRequestDto {

    private Long userId;
    private String postTitle;
    private String postContent;

    public PostRequestDto(Long userId, String postTitle, String postContent) {
        this.userId = userId;
        this.postTitle = postTitle;
        this.postContent = postContent;
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
}
