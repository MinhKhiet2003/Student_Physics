package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Edit extends AppCompatActivity {
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
        userAdapter = new UserAdapter(this, getListUser());
        rcvData.setAdapter(userAdapter);
            btnAddUser.setOnClickListener(view -> startActivity(new Intent(Edit.this, activity_AddUser.class)));

    }


    private List<User> getListUser() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.male, "Ta Minh Khiet","715105120", "Ninh Binh, Viet Nam", "01/01/2003", true));
        list.add(new User(R.drawable.male, "Nguyen Thanh Huy","715105120", "Quang Ninh, Viet Nam", "01/01/2003", true));
        list.add(new User(R.drawable.male, "Nguyen Duc Hung","715105120", "Ha Noi, Viet Nam", "01/01/2003", true));
        list.add(new User(R.drawable.female, "Tran Thao My","715105120", "Hai Phong, Viet Nam", "01/01/2003", false));
        list.add(new User(R.drawable.female, "Pham Ngoc Hoa","715105120", "Phu Tho, Viet Nam", "01/01/2003", false));
        list.add(new User(R.drawable.male, "Pham Minh Phuong","715105120", "Ninh Binh, Viet Nam", "01/01/2003", true));
        list.add(new User(R.drawable.male, "Nguyen Van Hao Lan","715105120", "Hai Phong, Viet Nam", "01/01/2003", true));
        list.add(new User(R.drawable.male, "Do Quang Huy","715105120", "Hai Phong, Viet Nam", "01/01/2003", true));

        return list;
    }

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
