package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Search extends AppCompatActivity {
    ImageButton home;
    ImageButton edit;
    ImageButton search;
    ImageButton menu;
    RelativeLayout layoutItemSeach;

    private RecyclerView rcvUser;
    private UserAdapter userAdapter;
    private SearchView searchView;
    private List<User> listUser = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addView();
        addEvents();
        rcvUser = findViewById(R.id.rcv_users);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvUser.setLayoutManager(linearLayoutManager);
        userAdapter = new UserAdapter(this,listUser);
        rcvUser.setAdapter(userAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcvUser.addItemDecoration(itemDecoration);
        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        // Fetch user data from the API
        fetchUsers();
    }

    private void fetchUsers() {
        ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
        Call<List<User>> callGet = apiService.getUsers();
        callGet.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    listUser.addAll(response.body());
                    for (User user : listUser) {
                        if (user.isGender()) {
                            user.setResourceID(R.drawable.male); // Set female avatar resource
                        } else {
                            user.setResourceID(R.drawable.female); // Set male avatar resource
                        }
                    }
                    userAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(Search.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(Search.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void filterList(String newText) {
        List<User> filteredList = new ArrayList<>();
        for (User user : listUser) {
            if (user.getFullName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(user);
            }
        }
        if (filteredList.isEmpty()) {
            Toast.makeText(this, "No data Found", Toast.LENGTH_SHORT).show();
        } else {
            userAdapter.setFilteredList(filteredList);
        }
    }
    private void addView() {
        home = findViewById(R.id.home);
        edit = findViewById(R.id.edit);
        search = findViewById(R.id.search);
        menu = findViewById(R.id.menu);
    }

    private void addEvents(){
        edit.setOnClickListener(v -> {
            startActivity(new Intent(Search.this, Edit.class));
            Toast.makeText(Search.this, "edit", Toast.LENGTH_SHORT).show();
        });

        home.setOnClickListener(v -> {
            startActivity(new Intent(Search.this, MainActivity.class));
            Toast.makeText(Search.this, "home", Toast.LENGTH_SHORT).show();
        });

        menu.setOnClickListener(v -> {
            startActivity(new Intent(Search.this, Menu.class));
            Toast.makeText(Search.this, "menu", Toast.LENGTH_SHORT).show();
        });
    }
}
