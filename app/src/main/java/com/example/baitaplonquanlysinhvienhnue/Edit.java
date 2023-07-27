package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit extends AppCompatActivity {
    private List<User> userList = new ArrayList<>();
    RecyclerView rcvData;
    UserAdapter userAdapter;
    ImageButton home;
    ImageButton edit;
    ImageButton search;
    ImageButton menu;
    private Button btnAddUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        addView();
        addEvents();
        rcvData = findViewById(R.id.rcv_data);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);
        userAdapter = new UserAdapter(this, userList);
        rcvData.setAdapter(userAdapter);
        btnAddUser.setOnClickListener(view -> startActivity(new Intent(Edit.this, activity_AddUser.class)));

        // Tạo adapter với danh sách người dùng ban đầu
//        userList = getListUser(); // Lấy danh sách người dùng ban đầu
        userAdapter = new UserAdapter(this, userList);
        rcvData.setAdapter(userAdapter);

        // Kiểm tra xem Intent có chứa thông tin người dùng không
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("user")) {
            User user = (User) intent.getSerializableExtra("user");
            // Thêm thông tin người dùng mới vào danh sách
            userList.add(user);
            // Cập nhật adapter
            userAdapter.notifyDataSetChanged();
        }
        fetchUsers();
    }


    private void fetchUsers() {
        ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
        Call<List<User>> callGet = apiService.getUsers();
        callGet.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    userList.addAll(response.body());
                    userAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Edit.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("API_CALL_ERROR", "API call failed", t);
                Toast.makeText(Edit.this, "API call failed", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @SuppressLint("WrongViewCast")
    private void addView(){
        home=findViewById(R.id.home);
        edit=findViewById(R.id.edit);
        search=findViewById(R.id.search);
        menu=findViewById(R.id.menu);
        btnAddUser = findViewById(R.id.btn_AddUser);
    }

    private void addEvents(){
        home.setOnClickListener(v -> {
            startActivity(new Intent(Edit.this, MainActivity.class));
            Toast.makeText(Edit.this, "home", Toast.LENGTH_SHORT).show();
        });

        search.setOnClickListener(v -> {
            startActivity(new Intent(Edit.this, Search.class));
            Toast.makeText(Edit.this, "search", Toast.LENGTH_SHORT).show();
        });

        menu.setOnClickListener(v -> {
            startActivity(new Intent(Edit.this, Menu.class));
            Toast.makeText(Edit.this, "menu", Toast.LENGTH_SHORT).show();
        });
        btnAddUser.setOnClickListener(v -> startActivity(new Intent(Edit.this, activity_AddUser.class)));
    }
}
