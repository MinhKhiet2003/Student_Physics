package com.example.baitaplonquanlysinhvienhnue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
            String name = etName.getText().toString().trim();
            String msv = etMsv.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String dateOfBirth = etDateOfBirth.getText().toString().trim();
            String className = etclassName.getText().toString().trim();
            String Email = etEmail.getText().toString().trim();
            String PhoneNumber = etPhoneNumber.getText().toString().trim();
            int genderID = rgGender.getCheckedRadioButtonId();
            boolean isMale = genderID == R.id.rbMale;

            // Cập nhật thông tin người dùng trong đối tượng user
            user.setFullName(name);
            user.setStudentId(msv);
            user.setHome(address);
            user.setDate(dateOfBirth);
            user.setGender(isMale);

//             Hiển thị thông tin đã cập nhật lên Toast hoặc có thể chuyển dữ liệu về Activity trước (activity_Edit) để cập nhật danh sách người dùng.
            String userInfo = "Thông tin đã được cập nhật:\n"
                    + "Họ và tên: " + name + "\n"
                    + "Mã sinh viên: " + msv + "\n"
                    + "Địa chỉ: " + address + "\n"
                    + "Ngày sinh: " + dateOfBirth + "\n"
                    + "Giới tính: " + (isMale ? "Nam" : "Nữ") + "\n"
                    + "lớp: " + className + "\n"
                    + "email: " + Email + "\n"
                    + "Phone: " + PhoneNumber;



            Toast.makeText(EditUserProfileActivity.this, userInfo, Toast.LENGTH_LONG).show();

//             Nếu bạn muốn cập nhật danh sách người dùng ở Activity trước, hãy sử dụng Intent để gửi dữ liệu về.
//             Ví dụ: Intent resultIntent = new Intent();
//             resultIntent.putExtra("updated_user", user);
//             setResult(RESULT_OK, resultIntent);
             finish();
        });
    }
}

