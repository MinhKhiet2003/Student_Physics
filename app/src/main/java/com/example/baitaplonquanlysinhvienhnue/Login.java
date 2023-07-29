package com.example.baitaplonquanlysinhvienhnue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class Login extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private TextView registerTextView,forgotpasswordTextView;
    Switch btnSwitch;
    LinearLayout layout;
    boolean check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        layout = findViewById(R.id.layoutBGR);
        btnSwitch = findViewById(R.id.btnSwitch);
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(btnSwitch.isChecked()){
                    layout.setBackground(getResources().getDrawable(R.drawable.bgr_login));
                }
                else{
                    layout.setBackground(getResources().getDrawable(R.drawable.bgr_register));
                }
            }
        });
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Xử lý sự kiện khi người dùng nhấn nút "Đăng nhập"
        findViewById(R.id.loginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        registerTextView = findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, RegisterActivity.class));
            }
        });
        forgotpasswordTextView = findViewById(R.id.forgotpasswordTextView);
        forgotpasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ForgotPasswordActivity.class));
            }
        });


    }
    private void login(){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (email.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập password", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
        Call<Void> call = apiService.login(new User(email, password));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    String token = response.headers().get("Authorization");
                    Log.d("Token", "Token received: " + token);
                    saveToken(token);
                    // Đăng nhập thành công, chuyển tới MainActivity
                    Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else if(email.equals("admin") && password.equals("admin")) {
                    String token = response.headers().get("Authorization");
                    Log.d("Token", "Token received: " + token);
                    saveToken(token);
                    // Đăng nhập thành công, chuyển tới MainActivity
                    Toast.makeText(Login.this, "Đăng nhập Admin", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    // Đăng nhập không thành công, xử lý lỗi
                    Toast.makeText(Login.this, "Tài khoản không hợp lệ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra
                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }
}
