package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        userAdapter = new UserAdapter(this, getListUser());
        rcvData.setAdapter(userAdapter);
        btnAddUser.setOnClickListener(view -> startActivity(new Intent(Edit.this, activity_AddUser.class)));

        // Tạo adapter với danh sách người dùng ban đầu
        userList = getListUser(); // Lấy danh sách người dùng ban đầu
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
    }


    private List<User> getListUser() {
        List<User> list = new ArrayList<>();
        list.add(new User(R.drawable.male, "Ta Minh Khiet","715105120", "Ninh Binh, Viet Nam", "01/01/2003", true,"K71E2", "stu715105120@hnue.edu.vn", "109853221"));
        list.add(new User(R.drawable.male, "Nguyen Thanh Huy","715105120", "Quang Ninh, Viet Nam", "01/01/2003", true, "K71E2","stu715105120@hnue.edu.vn", "109853221"));
        list.add(new User(R.drawable.male, "Nguyen Duc Hung","715105120", "Ha Noi, Viet Nam", "01/01/2003", true, "K71E2","stu715105120@hnue.edu.vn", "109853221"));
        list.add(new User(R.drawable.female, "Tran Thao My","715105120", "Hai Phong, Viet Nam", "01/01/2003", false, "K71E2","stu715105120@hnue.edu.vn", "109853221"));
        list.add(new User(R.drawable.female, "Pham Ngoc Hoa","715105120", "Phu Tho, Viet Nam", "01/01/2003", false, "K71E2","stu715105120@hnue.edu.vn", "109853221"));
        list.add(new User(R.drawable.male, "Pham Minh Phuong","715105120", "Ninh Binh, Viet Nam", "01/01/2003", true, "K71E2","stu715105120@hnue.edu.vn", "109853221"));
        list.add(new User(R.drawable.male, "Nguyen Van Hao Lan","715105120", "Hai Phong, Viet Nam", "01/01/2003", true,"K71E2","stu715105120@hnue.edu.vn", "109853221"));
        list.add(new User(R.drawable.male, "Do Quang Huy","715105120", "Hai Phong, Viet Nam", "01/01/2003", true,"K71E2","stu715105120@hnue.edu.vn", "109853221"));

        return list;
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
