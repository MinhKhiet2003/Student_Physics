package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//public class Menu extends AppCompatActivity {
//    ImageButton home;
//    ImageButton edit;
//    ImageButton search;
//    ImageButton menu;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_menu);
//    }
//    private void addView(){
//        home=findViewById(R.id.home);
//        edit=findViewById(R.id.edit);
//        search=findViewById(R.id.search);
//        menu=findViewById(R.id.menu);
//    }
//    private void addEvents(){
//        edit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Menu.this,Edit.class);
//                startActivity(intentEdit);
//                Toast.makeText(Menu.this, "edit", Toast.LENGTH_SHORT).show();
//
//            }
//        });
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Menu.this,Search.class);
//                startActivity(intentEdit);
//                Toast.makeText(Menu.this, "search", Toast.LENGTH_SHORT).show();
//            }
//        });
//        home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intentEdit=new Intent(Menu.this,MainActivity.class);
//                startActivity(intentEdit);
//                Toast.makeText(Menu.this, "menu", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Menu extends AppCompatActivity {
    ImageButton home;
    ImageButton edit;
    ImageButton search;
    ImageButton menu;
    TextView name;
    TextView phone;
    TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        addView();
        addEvents();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        Log.d("token ", "TOENNNNN" + token); // In log để kiểm tra user được trả về từ API
        ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
        Call<User> call = apiService.getProfile("Bearer " + token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    Log.d("User ", "User" + user);
                    // Usercom.example.baitaplonquanlysinhvienhnue.User@5af3bee
                    if (user != null) {
                        // Hiển thị thông tin profile lên TextView
                        name.setText(user.getFullName());
                        phone.setText(user.getPhoneNumber());
                        email.setText(user.getEmail());
                    }
                } else {
                    int errorCode = response.code();
                    Log.e("API_ERROR", "API call failed with error code: " + errorCode);
                    Toast.makeText(Menu.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("API_CALL_ERROR", "API call failed", t);
                Toast.makeText(Menu.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void addView(){
        home = findViewById(R.id.home);
        edit = findViewById(R.id.edit);
        search = findViewById(R.id.search);
        menu = findViewById(R.id.menu);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
    }
    private void addEvents(){

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Edit.class));
                Toast.makeText(Menu.this, "edit", Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, Search.class));
                Toast.makeText(Menu.this, "search", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menu.this, MainActivity.class));
                Toast.makeText(Menu.this, "home", Toast.LENGTH_SHORT).show();
            }
        });
    }
}