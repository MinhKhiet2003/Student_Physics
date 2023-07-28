package com.example.baitaplonquanlysinhvienhnue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserProfileActivity extends AppCompatActivity {
//    Context context;
     EditText etName;
     EditText etMsv;
     EditText etAddress;
     EditText etDateOfBirth;
     RadioGroup rgGender;
     User user;
    ImageView ivProfilePic;

    EditText etEmail;
    EditText etclassName;

     EditText etPhoneNumber;
    UserAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }

        // Ánh xạ các trường EditText và RadioGroup trong layout
        User user = (User) bundle.get("object_user");
        etName = findViewById(R.id.etName);
        etMsv = findViewById(R.id.etMsv);
        etAddress = findViewById(R.id.etAddress);
        etDateOfBirth = findViewById(R.id.etDateOfBirth);
        rgGender = findViewById(R.id.rgGender);
        etclassName = findViewById(R.id.etClassName);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        ivProfilePic = findViewById(R.id.ivProfilePic);


        // Lấy dữ liệu người dùng từ Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("object_user")) {
//             Hiển thị thông tin người dùng lên giao diện
            etName.setText(user.getFullName());
            etMsv.setText(user.getStudentId());
            etAddress.setText(user.getHome());
            etDateOfBirth.setText(user.getDate());
            etclassName.setText(user.getClassName());
            etEmail.setText(user.getEmail());
            etPhoneNumber.setText(user.getPhoneNumber());




            // Chọn giới tính dựa vào thông tin người dùng
            if (user.isGender()) {
                rgGender.check(R.id.rbMale);
            } else {
                rgGender.check(R.id.rbFemale);
            }

            // Xử lý sự kiện khi RadioGroup được chọn
            rgGender.setOnCheckedChangeListener((group, checkedId) -> {
                // Kiểm tra xem RadioButton nào được chọn
                if (checkedId == R.id.rbMale) {
                    // Nếu chọn rbMale, đổi ảnh trong ImageView thành hình ảnh male
                    ivProfilePic.setImageResource(R.drawable.male);
                } else if (checkedId == R.id.rbFemale) {
                    // Nếu chọn rbFemale, đổi ảnh trong ImageView thành hình ảnh female
                    ivProfilePic.setImageResource(R.drawable.female);
                }
            });
        }

        // Xử lý sự kiện khi nhấn nút "Lưu"
        Button btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(v -> {
            // Lấy thông tin người dùng đã chỉnh sửa
            String fullName = etName.getText().toString().trim();
            String studentId = etMsv.getText().toString().trim();
            String home = etAddress.getText().toString().trim();
            String date = etDateOfBirth.getText().toString().trim();
            String studentClass = etclassName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phoneNumber = etPhoneNumber.getText().toString().trim();
            int genderID = rgGender.getCheckedRadioButtonId();
            boolean isMale = genderID == R.id.rbMale;
            ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
            // Cập nhật thông tin người dùng trong đối tượng user
            User userUpdate=new User(studentId,fullName,date,home,studentClass,isMale,email,phoneNumber);
            Log.d("test", "StudentID " + userUpdate.getClassName());
            apiService.updateUser(userUpdate.getStudentId(), userUpdate).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        // Xử lý phản hồi thành công ở đây (ví dụ: hiển thị thông báo thành công)
                        Toast.makeText(EditUserProfileActivity.this, "Đã cập nhật thông tin người dùng thành công", Toast.LENGTH_SHORT).show();
                        fetchUpdatedStudentList();
                        Intent intent = new Intent(EditUserProfileActivity.this, Edit.class);
                        startActivity(intent);
                        finish(); // Đóng hoạt động sau khi cập nhật thành công
                    } else {
                        // Xử lý phản hồi không thành công ở đây (ví dụ: hiển thị thông báo lỗi)
                        Toast.makeText(EditUserProfileActivity.this, "Không thể cập nhật thông tin người dùng", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    // Xử lý lỗi khi gọi API (ví dụ: lỗi kết nối mạng)
                    Toast.makeText(EditUserProfileActivity.this, "Không thể kết nối với máy chủ", Toast.LENGTH_SHORT).show();
                }
            });


//             Hiển thị thông tin đã cập nhật lên Toast hoặc có thể chuyển dữ liệu về Activity trước (activity_Edit) để cập nhật danh sách người dùng.
            String userInfo = "Thông tin đã được cập nhật:\n"
                    + "Họ và tên: " + fullName + "\n"
                    + "Mã sinh viên: " + studentId+ "\n"
                    + "Địa chỉ: " + home + "\n"
                    + "Ngày sinh: " + date + "\n"
                    + "Giới tính: " + (isMale ? "Nam" : "Nữ") + "\n"
                    + "lớp: " + studentClass + "\n"
                    + "email: " + email + "\n"
                    + "Phone: " + phoneNumber;



            Toast.makeText(EditUserProfileActivity.this, userInfo, Toast.LENGTH_LONG).show();

//             Nếu bạn muốn cập nhật danh sách người dùng ở Activity trước, hãy sử dụng Intent để gửi dữ liệu về.
//             Ví dụ: Intent resultIntent = new Intent();
//             resultIntent.putExtra("updated_user", user);
//             setResult(RESULT_OK, resultIntent);

             finish();
        });
    }
    private void fetchUpdatedStudentList() {
        ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> updatedStudentList = response.body();
                    updateStudentListOnUI(updatedStudentList);

                } else {
                    // Xử lý phản hồi không thành công ở đây (ví dụ: hiển thị thông báo lỗi)
                    Toast.makeText(EditUserProfileActivity.this, "Không thể lấy danh sách sinh viên", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                // Xử lý lỗi khi gọi API (ví dụ: lỗi kết nối mạng)
                Toast.makeText(EditUserProfileActivity.this, "Không thể kết nối với máy chủ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateStudentListOnUI(List<User> updatedStudentList) {
        if (adapter == null) {
            // Nếu chưa được khởi tạo, tạo một adapter mới và thiết lập nó cho RecyclerView
            adapter = new UserAdapter(this, updatedStudentList);
            RecyclerView recyclerView = findViewById(R.id.rcv_data);
            recyclerView.setAdapter(adapter);
        } else {
            // Nếu đã có adapter, cập nhật danh sách sinh viên và thông báo cho adapter biết dữ liệu đã thay đổi
            adapter.setFilteredList(updatedStudentList);
            adapter.notifyDataSetChanged();
        }
    }

}

