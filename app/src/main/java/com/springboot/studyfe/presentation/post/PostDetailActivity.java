package com.springboot.studyfe.presentation.post;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.springboot.studyfe.R;
import com.springboot.studyfe.data.api.PostApi;
import com.springboot.studyfe.data.api.RetrofitClient;
import com.springboot.studyfe.data.dto.PostResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDetailActivity extends AppCompatActivity {

    private TextView textTitle, textContent, textCreatedAt;
    private PostApi postApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textTitle = findViewById(R.id.textTitle);
        textContent = findViewById(R.id.textContent);
        textCreatedAt = findViewById(R.id.textCreatedAt);

        Long postId = getIntent().getLongExtra("postId", -1);
        if (postId == -1) {
            Toast.makeText(this, "잘못된 게시물 ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        postApi = RetrofitClient.getInstance().create(PostApi.class);

        Call<PostResponseDto> call = postApi.getPostById(postId);
        call.enqueue(new Callback<PostResponseDto>() {
            @Override
            public void onResponse(Call<PostResponseDto> call, Response<PostResponseDto> response) {
                if (response.isSuccessful()) {
                    PostResponseDto post = response.body();
                    textTitle.setText(post.getPostTitle());
                    textContent.setText(post.getPostContent());
                    textCreatedAt.setText("작성일: " + post.getCreatedAt());
                } else {
                    Toast.makeText(PostDetailActivity.this, "게시물 조회 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponseDto> call, Throwable t) {
                Toast.makeText(PostDetailActivity.this, "서버 오류 발생", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
