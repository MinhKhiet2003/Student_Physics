package com.example.baitaplonquanlysinhvienhnue;

import android.content.Intent;
import android.os.Bundle;
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
        Call<List<User>> call = apiService.getUser();

        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                if (response.isSuccessful()) {
                    List<User> users = response.body();

//                    StringBuilder names = new StringBuilder();
                    for (User user : users) {
//                        names.append(user.getName()).append("\n");
                        if(email.equals(user.getEmail())  && password.equals(user.getPassword())){
                            check = true;
                            break;
                        }
                        else{
                            check = false;
                        }
                    }
                    if(check == true){
                        Toast.makeText(Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login.this, "Tài khoản không hợp lệ", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Có lỗi ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra
                Toast.makeText(Login.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
