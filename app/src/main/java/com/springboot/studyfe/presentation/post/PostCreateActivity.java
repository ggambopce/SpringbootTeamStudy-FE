package com.springboot.studyfe.presentation.post;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.springboot.studyfe.R;
import com.springboot.studyfe.data.api.PostApi;
import com.springboot.studyfe.data.api.RetrofitClient;
import com.springboot.studyfe.data.dto.PostRequestDto;
import com.springboot.studyfe.data.dto.PostResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostCreateActivity extends AppCompatActivity {
    private EditText editTitle, editContent;
    private Button buttonSubmit;
    private PostApi postApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create); // XML 필요

        editTitle = findViewById(R.id.editTitle);
        editContent = findViewById(R.id.editContent);
        buttonSubmit = findViewById(R.id.buttonSave);

        postApi = RetrofitClient.getInstance().create(PostApi.class);

        buttonSubmit.setOnClickListener(v -> createPost());
    }

    private void createPost() {
        String title = editTitle.getText().toString();
        String content = editContent.getText().toString();
        Long userId = 1L; // 고정값, 인증 없이 임시작성

        if (title.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "모든 항목을 입력하세요", Toast.LENGTH_SHORT).show();
            return;
        }

        PostRequestDto dto = new PostRequestDto(userId, title, content);

        postApi.createPost(dto).enqueue(new Callback<PostResponseDto>() {
            @Override
            public void onResponse(Call<PostResponseDto> call, Response<PostResponseDto> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(PostCreateActivity.this, "게시글 등록 성공", Toast.LENGTH_SHORT).show();
                    Log.d("PostCreate", "성공: " + response.body().getPostTitle());
                } else {
                    Toast.makeText(PostCreateActivity.this, "응답 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.e("PostCreate", "에러: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PostResponseDto> call, Throwable t) {
                Toast.makeText(PostCreateActivity.this, "통신 오류", Toast.LENGTH_SHORT).show();
                Log.e("PostCreate", "실패", t);
            }
        });
    }
}
