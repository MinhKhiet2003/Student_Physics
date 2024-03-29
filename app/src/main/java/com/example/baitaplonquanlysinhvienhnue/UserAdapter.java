package com.example.baitaplonquanlysinhvienhnue;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userViewHolder>{
    private List<User> listUser = new ArrayList<User>();
    Context context;
    int swipedPosition = -1;
    UserAdapter adapter;

    public UserAdapter(List<User> listUser) {
        this.listUser = listUser;
    }
    public void setFilteredList(List<User> filterdList){
        this.listUser=filterdList;
        notifyDataSetChanged();
    }    public void updateSwipedPosition(int position) {
        swipedPosition = position;
    }

    public UserAdapter(Context context, List<User> listUser) {
        this.context = context;
        this.listUser = listUser;
        adapter = this;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent, false);
        return new userViewHolder(view);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final User user = listUser.get(position);
        if (user == null) {
            return;
        }

        holder.imgAvatar.setImageResource(user.getResourceID());
        holder.tvName.setText(user.getFullName());
        holder.tvID.setText(user.getStudentId());
//        holder.layoutItem.setOnClickListener(view -> onclickGoToProfileUser(user));


        holder.layoutItem.setOnTouchListener(new OnSwipeTouchListener(context, adapter) {
            @Override
            public void onSwipeLeft() {
                // Kiểm tra nếu không ở trạng thái click, thực hiện vuốt trái
                showDeleteAlertDialog(user.getFullName(), position);
            }
            // Phương thức hiển thị AlertDialog để xác nhận việc xoá
            private void showDeleteAlertDialog(String userName, int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xoá người dùng");
                builder.setMessage("Bạn có chắc chắn muốn xoá người dùng " + userName + " không?");
                builder.setPositiveButton("Có", (dialog, which) -> {
                    // Xử lý hành động Delete ở đây
//                    listUser.remove(position);
//                    notifyDataSetChanged();
//                    setSwipedPosition(-1);
//                    Toast.makeText(context, "Deleted user: " + userName, Toast.LENGTH_SHORT).show();
                    String userId = user.getStudentId();
                    deleteUserFromServer(userId, position, userName);
                });
                builder.setNegativeButton("Không", (dialog, which) -> {
                    // Không làm gì cả, đơn giản chỉ đóng AlertDialog
                });
                builder.show();
            }

            @Override
            public void onSwipeRight() {
                Intent intentEditProfileUser = new Intent(context, EditUserProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_user", user);
                intentEditProfileUser.putExtras(bundle);
                context.startActivity(intentEditProfileUser);
//                onclickGoToProfileUser(user);

            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onTouchDown() {
                // Xác định vị trí của item được vuốt để hiển thị các nút Edit và Delete
                swipedPosition = holder.getBindingAdapterPosition();
                notifyDataSetChanged();
            }
            @Override
            public void onTouchUp() {

            }
        });

        // Hiển thị hoặc ẩn button Edit và Delete dựa trên trạng thái vuốt
        holder.setSwipeState(swipedPosition == position);
    }



    private void deleteUserFromServer(String userId, int position, String userName) {
        ApiSevice apiService = RetrofitClient.getRetrofitInstance().create(ApiSevice.class);
        Call<Void> call = apiService.deleteUser(userId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // User deleted successfully from the server
                    listUser.remove(position);
                    notifyDataSetChanged();
                    adapter.updateSwipedPosition(-1);
                    Toast.makeText(context, "Deleted user: " + userName, Toast.LENGTH_SHORT).show();
                } else {
                    // Handle error response
                    Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle network or other errors
                Toast.makeText(context, "Failed to delete user", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void onclickGoToProfileUser(User user) {
        Intent intentProfileUser = new Intent(context, activity_profile_User.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_user", user);
        intentProfileUser.putExtras(bundle);
        context.startActivity(intentProfileUser);
    }
    @Override
    public int getItemCount() {
        if (listUser != null){
            return listUser.size();
        }
        return 0;
    }

    public static class userViewHolder extends RecyclerView.ViewHolder {
        private boolean isSwiped = false;
        RelativeLayout layoutItem;
        ImageView imgAvatar;
        TextView tvName;
        TextView tvID;

//        Button btnEdit;
        Button btnDelete;
        LinearLayout layoutButtons;
        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_itemUser);
            imgAvatar = itemView.findViewById(R.id.img_avt);
            tvName = itemView.findViewById(R.id.name_user);
            tvID = itemView.findViewById(R.id.msv_user);
            layoutButtons = itemView.findViewById(R.id.layoutButtons);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
        // Phương thức điều chỉnh hiển thị của button theo trạng thái vuốt
        public void setSwipeState(boolean isSwiped) {
            this.isSwiped = isSwiped;
            if (isSwiped) {
                layoutButtons.setVisibility(View.VISIBLE);
            } else {
                layoutButtons.setVisibility(View.GONE);
            }
        }
    }
}
