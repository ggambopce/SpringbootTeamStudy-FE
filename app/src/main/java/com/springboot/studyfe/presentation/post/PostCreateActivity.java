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
import com.springboot.studyfe.data.entity.Board;

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

        Board requestBoard = new Board(userId, title, content);

        postApi.createPost(requestBoard).enqueue(new Callback<Board>() {
            @Override
            public void onResponse(Call<Board> call, Response<Board> response) {
                if (response.isSuccessful()) {
                    Board responseBoard = response.body();
                    Toast.makeText(PostCreateActivity.this, "게시글 등록 성공", Toast.LENGTH_SHORT).show();
                    Log.d("PostCreate", "성공: " + responseBoard.getPostTitle());
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Log.e("PostCreate", "응답 실패 코드: " + response.code() + "\n에러 내용: " + errorBody);
                    } catch (Exception e) {
                        Log.e("PostCreate", "에러 바디 파싱 실패", e);
                    }
                    Toast.makeText(PostCreateActivity.this, "응답 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Board> call, Throwable t) {
                Toast.makeText(PostCreateActivity.this, "통신 오류", Toast.LENGTH_SHORT).show();
                Log.e("PostCreate", "실패", t);
            }
        });
    }
}
