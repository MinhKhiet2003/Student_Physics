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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText,phoneEditText, emailEditText, passwordEditText;
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
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        quaylaiDangNhap = findViewById(R.id.quaylaiDangNhap);
        phoneEditText = findViewById(R.id.phoneEditText);
        nameEditText.setTextColor(Color.parseColor("#FFFFFF"));
        phoneEditText.setTextColor(Color.parseColor("#FFFFFF"));
        emailEditText.setTextColor(Color.parseColor("#FFFFFF"));
        passwordEditText.setTextColor(Color.parseColor("#FFFFFF"));
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
        Call<List<User>> callGet = apiService.getUser();
        callGet.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
             //   Toast.makeText(RegisterActivity.this,"Connect API",Toast.LENGTH_SHORT).show();
                List<User> users = response.body();
                Log.d("213",users+"");
                for (User user : users) {
                    if(user.getEmail().equals(email) ){
                        checkEmail = true;
                        break;
                    }
                    else {
                        checkEmail = false;
                    }
                }
                if(checkEmail == true){
                    Toast.makeText(RegisterActivity.this,"Email đã tồn tại",Toast.LENGTH_SHORT).show();
                }
                else{
                    Call<Void> callPost = apiService.register(user);
                    callPost.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            // Xử lý khi có lỗi xảy ra
                            Toast.makeText(RegisterActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });

    }
}
