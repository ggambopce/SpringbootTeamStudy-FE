package com.springboot.studyfe.data.api;


import com.springboot.studyfe.data.dto.PostRequestDto;
import com.springboot.studyfe.data.dto.PostResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApi {

    @POST("/api/board")
    Call<PostResponseDto> createPost(@Body PostRequestDto dto);

    @GET("/api/board")
    Call<List<PostResponseDto>> getAllPosts();

    @GET("/api/board/{postId}")
    Call<PostResponseDto> getPostById(@Path("postId") Long postId);
}
