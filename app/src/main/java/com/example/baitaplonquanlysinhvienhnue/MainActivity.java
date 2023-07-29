package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageButton home;
    ImageButton edit;
    ImageButton search;
    ImageButton menu;
    ImageView dot1,dot2,dot3;
    ImageView dot4,dot5,dot6,dot7,dot8,dot9;
    private int[] slideshowImages = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3,
            R.drawable.img4,
            R.drawable.img5,
            R.drawable.img6,
            R.drawable.img7,
            R.drawable.img8,
            R.drawable.img9,
            // Thêm thêm ID tài nguyên ảnh nếu cần
    };
    private LinearLayout dotLayout;
    private ImageView[] dots;
    private ViewPager viewPager;
    private SlideshowPagerAdapter slideshowPagerAdapter;
    private int currentPage = 0;
    private Timer timer;
    private final long DELAY_MS = 3000; // 3 giây
    private final long PERIOD_MS = 3000; // 3 giây

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addView();
        addEvents();
        viewPager = findViewById(R.id.layout_home_slideshow);
        slideshowPagerAdapter = new SlideshowPagerAdapter(this, slideshowImages);
        viewPager.setAdapter(slideshowPagerAdapter);

        List<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.home01);
        imageList.add(R.drawable.home02);
        imageList.add(R.drawable.home03);
        // Thêm tấm ảnh khác từ drawable vào đây

        RecyclerView recyclerView = findViewById(R.id.listImage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        ImageAdapter imageAdapter = new ImageAdapter(imageList);
        recyclerView.setAdapter(imageAdapter);

        dot1 = findViewById(R.id.dot1);
        dot2 = findViewById(R.id.dot2);
        dot3 = findViewById(R.id.dot3);
        dot4 = findViewById(R.id.dot4);
        dot5 = findViewById(R.id.dot5);
        dot6 = findViewById(R.id.dot6);
        dot7 = findViewById(R.id.dot7);
        dot8 = findViewById(R.id.dot8);
        dot9 = findViewById(R.id.dot9);

        // Tự động trượt
        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage == slideshowImages.length) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
                if (currentPage == 1) {
                    dot1.setImageResource(R.drawable.ic_dot_active);
                    dot2.setImageResource(R.drawable.ic_dot_inactive);
                    dot9.setImageResource(R.drawable.ic_dot_inactive);
                } else if (currentPage == 2) {
                    dot2.setImageResource(R.drawable.ic_dot_active);
                    dot1.setImageResource(R.drawable.ic_dot_inactive);
                } else if (currentPage == 3) {
                    dot3.setImageResource(R.drawable.ic_dot_active);
                    dot2.setImageResource(R.drawable.ic_dot_inactive);
                }else if (currentPage == 4) {
                    dot4.setImageResource(R.drawable.ic_dot_active);
                    dot3.setImageResource(R.drawable.ic_dot_inactive);
                }else if (currentPage == 5) {
                    dot5.setImageResource(R.drawable.ic_dot_active);
                    dot4.setImageResource(R.drawable.ic_dot_inactive);
                }else if (currentPage == 6) {
                    dot6.setImageResource(R.drawable.ic_dot_active);
                    dot5.setImageResource(R.drawable.ic_dot_inactive);
                }else if (currentPage == 7) {
                    dot7.setImageResource(R.drawable.ic_dot_active);
                    dot6.setImageResource(R.drawable.ic_dot_inactive);
                }else if (currentPage == 8) {
                    dot8.setImageResource(R.drawable.ic_dot_active);
                    dot7.setImageResource(R.drawable.ic_dot_inactive);
                }else if (currentPage == 9) {
                    dot9.setImageResource(R.drawable.ic_dot_active);
                    dot8.setImageResource(R.drawable.ic_dot_inactive);
                    dot1.setImageResource(R.drawable.ic_dot_inactive);
                }
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    private void addView(){
        home=findViewById(R.id.home);
        edit=findViewById(R.id.edit);
        search=findViewById(R.id.search);
        menu=findViewById(R.id.menu);
    }

    private void addEvents(){
        edit.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Edit.class));
            Toast.makeText(MainActivity.this, "edit", Toast.LENGTH_SHORT).show();
        });

        search.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Search.class));
            Toast.makeText(MainActivity.this, "search", Toast.LENGTH_SHORT).show();
        });

        menu.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, Menu.class));
            Toast.makeText(MainActivity.this, "menu", Toast.LENGTH_SHORT).show();
        });
    }
}
