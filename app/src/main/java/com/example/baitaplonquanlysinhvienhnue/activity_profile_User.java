package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_profile_User extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        User user = (User) bundle.get("object_user");
        TextView tvNameUser = findViewById(R.id.tvNameUser);
        TextView tvMSV = findViewById(R.id.tvStudentID);
        TextView tvGioiTinh = findViewById(R.id.tvGender);
        TextView tvAddress = findViewById(R.id.tvAddress);
        ImageView imgAvt = findViewById(R.id.profileImage);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvClassName = findViewById(R.id.tvClassName);
        TextView tvPhoneNumber = findViewById(R.id.tvPhoneNumber);
        TextView tvDateOfBirth = findViewById(R.id.tvDateOfBirth);
        if (user.getGioiTinh() == true){
            tvGioiTinh.setText("Giới tính: Nam");
            imgAvt.setImageResource(R.drawable.male);
        }else{
            tvGioiTinh.setText("Giới tính: Nữ");
            imgAvt.setImageResource(R.drawable.female);
        }
        tvClassName.setText("Lớp: " + user.getClassName());
        tvEmail.setText("Email: " + user.getEmail());
        tvPhoneNumber.setText("Số diện thoại: " + user.getPhoneNumber());
        tvMSV.setText(user.getMsv());
        tvNameUser.setText(user.getName());
        tvAddress.setText("Địa chỉ: " + user.getAddress());
        tvDateOfBirth.setText(user.getDateOfBirth());

    }
}