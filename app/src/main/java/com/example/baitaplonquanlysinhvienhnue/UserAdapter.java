package com.example.baitaplonquanlysinhvienhnue;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.userViewHolder>{
    List<User> listUser;
    Context context;
    RecyclerView rcvData;

    public UserAdapter(Context context, List<User> listUser) {
        this.context = context;
        this.listUser = listUser;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent, false);
        return new userViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder holder, int position) {
        final User user = listUser.get(position);
        if (user == null){
            return;
        }

        holder.imgAvatar.setImageResource(user.getResourceID());
        holder.tvName.setText(user.getName());
        holder.tvID.setText(user.getMsv());
        holder.layoutItem.setOnClickListener(view -> onclickGoToProfileUser(user));

        // Khởi tạo và thiết lập ItemTouchHelper cho mỗi viewHolder
        ItemTouchHelper.Callback callback = new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return makeMovementFlags(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // Không làm gì ở đây, vì chúng ta không hỗ trợ di chuyển item trong list
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Xử lý sự kiện vuốt sang trái (hoặc phải) ở đây
                int position = viewHolder.getBindingAdapterPosition();
                User user = listUser.get(position);
                if (direction == ItemTouchHelper.LEFT) {
                    // Xử lý hành động Delete ở đây
                    listUser.remove(position);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Deleted user: " + user.getName(), Toast.LENGTH_SHORT).show();
                } else if (direction == ItemTouchHelper.RIGHT) {
                    // Xử lý hành động Edit ở đây
                    // Ví dụ: Gửi dữ liệu user đến activity_EditUser để chỉnh sửa thông tin
                    Toast.makeText(context, "Edit clicked for user: " + user.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        // Khai báo một biến cho ItemTouchHelper
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rcvData);

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
        RelativeLayout layoutItem;
        ImageView imgAvatar;
        TextView tvName;
        TextView tvID;
        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutItem = itemView.findViewById(R.id.layout_itemUser);
            imgAvatar = itemView.findViewById(R.id.img_avt);
            tvName = itemView.findViewById(R.id.name_user);
            tvID = itemView.findViewById(R.id.msv_user);
        }
    }
}
