package com.springboot.studyfe.presentation.post;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.springboot.studyfe.R;
import com.springboot.studyfe.data.api.PostApi;
import com.springboot.studyfe.data.api.RetrofitClient;
import com.springboot.studyfe.data.dto.PostResponseDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostListActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> postTitles = new ArrayList<>();
    private PostApi postApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.postListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postTitles);
        listView.setAdapter(adapter);

        postApi = RetrofitClient.getInstance().create(PostApi.class);

        Call<List<PostResponseDto>> call = postApi.getAllPosts();
        call.enqueue(new Callback<List<PostResponseDto>>() {
            @Override
            public void onResponse(Call<List<PostResponseDto>> call, Response<List<PostResponseDto>> response) {
                if (response.isSuccessful()) {
                    for (PostResponseDto post : response.body()) {
                        postTitles.add(post.getPostTitle() + " - " + post.getCreatedAt());
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(PostListActivity.this, "불러오기 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PostResponseDto>> call, Throwable t) {
                Toast.makeText(PostListActivity.this, "서버 요청 실패", Toast.LENGTH_SHORT).show();
                Log.e("PostListActivity", "오류: " + t.getMessage());
            }
        });
    }
}
