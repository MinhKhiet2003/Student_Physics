package com.example.baitaplonquanlysinhvienhnue;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText,phoneEditText, emailEditText, passwordEditText;
    TextView textViewRegister;
    boolean checkEmail = false;
    Button quaylaiDangNhap;
    Switch btnSwitch;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        layout = findViewById(R.id.layoutBGR);
        btnSwitch = findViewById(R.id.btnSwitch);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        quaylaiDangNhap = findViewById(R.id.quaylaiDangNhap);
        phoneEditText = findViewById(R.id.phoneEditText);
        textViewRegister = findViewById(R.id.textViewRegister);
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(btnSwitch.isChecked()){
                    layout.setBackground(getResources().getDrawable(R.drawable.bgr_login));
                    setThemeColors(Color.parseColor("#000000"));

                }
                else{
                    layout.setBackground(getResources().getDrawable(R.drawable.bgr_register));
                    setThemeColors(Color.parseColor("#FFFFFF"));
                }
            }
        });
        setThemeColors(Color.parseColor("#FFFFFF"));
        quaylaiDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,Login.class));
            }
        });
        // Xử lý sự kiện đăng ký
        findViewById(R.id.registerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(RegisterActivity.this, "123", Toast.LENGTH_SHORT).show();
                register();

            }
        });
    }
    private void register() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();
        if (name.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập tên", Toast.LENGTH_SHORT).show();
            return;
        }

        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (phone.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(name,phone,email,password);
        // Gửi yêu cầu API đến endpoint "register"
        ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
        Call<User> callPost = apiService.register(user);
        callPost.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Email đã tồn tại ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
               // Log.d("Loi dang ky",t.getMessage());
                // Xử lý khi có lỗi xảy ra
                Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setThemeColors(int textColor) {
        nameEditText.setTextColor(textColor);
        phoneEditText.setTextColor(textColor);
        emailEditText.setTextColor(textColor);
        passwordEditText.setTextColor(textColor);
        textViewRegister.setTextColor(textColor);
        //
        nameEditText.setHintTextColor(textColor);
        phoneEditText.setHintTextColor(textColor);
        emailEditText.setHintTextColor(textColor);
        passwordEditText.setHintTextColor(textColor);
    }
}

