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
import com.springboot.studyfe.data.entity.Board;

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
        setContentView(R.layout.activity_home); // listView는 activity_main.xml에 있어야 함

        listView = findViewById(R.id.postListView);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, postTitles);
        listView.setAdapter(adapter);

        postApi = RetrofitClient.getInstance().create(PostApi.class);

        Call<List<Board>> call = postApi.getAllPosts();
        call.enqueue(new Callback<List<Board>>() {
            @Override
            public void onResponse(Call<List<Board>> call, Response<List<Board>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    for (Board board : response.body()) {
                        String title = board.getPostTitle();
                        String createdAt = board.getCreatedAt() != null ? board.getCreatedAt() : "날짜 없음";
                        postTitles.add(title + " - " + createdAt);
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(PostListActivity.this, "불러오기 실패: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Board>> call, Throwable t) {
                Toast.makeText(PostListActivity.this, "서버 요청 실패", Toast.LENGTH_SHORT).show();
                Log.e("PostListActivity", "오류: " + t.getMessage());
            }
        });
    }
}
