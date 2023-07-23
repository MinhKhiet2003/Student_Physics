package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class activity_AddUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int PICK_IMAGE_REQUEST = 1;
        ImageView ivProfilePic;
        RadioGroup rgGender;
        Button btnSave;
        Button btnChooseImage;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user2);
        ivProfilePic = findViewById(R.id.ivProfilePic);
        rgGender = findViewById(R.id.rgGender);
        btnSave = findViewById(R.id.btnSave);
        btnChooseImage = findViewById(R.id.btnChooseImage);

        // Xử lý sự kiện khi nhấn vào nút "Chọn ảnh"
        btnChooseImage.setOnClickListener(v -> {
            // Mở Intent để chọn ảnh từ thư viện
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), PICK_IMAGE_REQUEST);
        });
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

//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//                // Lấy URI của ảnh đã chọn
//                // và hiển thị ảnh lên ImageView
//                ivProfilePic.setImageURI(data.getData());
//            }
//        }

        // Xử lý sự kiện khi nhấn nút "Thêm"
        btnSave.setOnClickListener(v -> {
            TextView etName = findViewById(R.id.etName);
            TextView etAddress = findViewById(R.id.etAddress);
            TextView etMsv = findViewById(R.id.etMsv);
            TextView etDateOfBirth = findViewById(R.id.etDateOfBirth);


            // Tạo đối tượng User mới từ dữ liệu được nhập
            String name = etName.getText().toString().trim();
            String msv = etMsv.getText().toString().trim();
            String address = etAddress.getText().toString().trim();
            String dateOfBirth = etDateOfBirth.getText().toString().trim();
            int genderID = rgGender.getCheckedRadioButtonId();
            boolean isMale = genderID == R.id.rbMale;

//                // Kiểm tra xem đã chọn ảnh hay chưa
//                Drawable profilePic = ivProfilePic.getDrawable();
//                if (profilePic == null) {
//                    // Nếu chưa chọn ảnh, có thể hiển thị thông báo để người dùng chọn ảnh trước khi lưu
//                    // Ví dụ: Toast.makeText(activity_AddUser.this, "Vui lòng chọn ảnh trước khi lưu", Toast.LENGTH_SHORT).show();
//                    return;
//                }
            // Hiển thị thông tin User bằng Toast
            String userInfo = "Họ và tên: " + name + "\n"
                    + "Mã sinh viên: " + msv + "\n"
                    + "Địa chỉ: " + address + "\n"
                    + "Ngày sinh: " + dateOfBirth + "\n"
                    + "Giới tính: " + (isMale ? "Nam" : "Nữ");

            Toast.makeText(activity_AddUser.this, userInfo, Toast.LENGTH_LONG).show();

        });
    }
}