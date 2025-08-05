package com.springboot.studyfe.data.api;

import android.telecom.Call;

import com.springboot.studyfe.data.dto.PostRequestDto;
import com.springboot.studyfe.data.dto.PostResponseDto;

public interface PostApi {

    @POST("/api/board")
    Call<PostResponseDto> createPost(@Body PostRequestDto dto);
}
