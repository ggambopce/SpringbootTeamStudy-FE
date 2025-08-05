package com.springboot.studyfe.data.api;


import com.springboot.studyfe.data.dto.PostRequestDto;
import com.springboot.studyfe.data.dto.PostResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PostApi {

    @POST("/api/board")
    Call<PostResponseDto> createPost(@Body PostRequestDto dto);
}
