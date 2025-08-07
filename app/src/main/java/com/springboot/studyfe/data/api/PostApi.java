package com.springboot.studyfe.data.api;


import com.springboot.studyfe.data.dto.PostRequestDto;
import com.springboot.studyfe.data.dto.PostResponseDto;
import com.springboot.studyfe.data.entity.Board;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostApi {

    @POST("/api/board")
    Call<Board> createPost(@Body Board board);

    // 게시물 전체 조회
    @GET("/api/board")
    Call<List<Board>> getAllPosts();

    // 게시물 단건 조회
    @GET("/api/board/{postId}")
    Call<Board> getPostById(@Path("postId") Long postId);
}
