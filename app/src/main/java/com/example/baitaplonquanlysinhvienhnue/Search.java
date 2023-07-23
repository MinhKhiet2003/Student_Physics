package com.example.baitaplonquanlysinhvienhnue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
public class Search extends AppCompatActivity {
    ImageButton home;
    ImageButton edit;
    ImageButton search;
    ImageButton menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        addView();
        addEvents();
    }

    private void addView(){
        home=findViewById(R.id.home);
        edit=findViewById(R.id.edit);
        search=findViewById(R.id.search);
        menu=findViewById(R.id.menu);
    }

    private void addEvents(){
        edit.setOnClickListener(v -> {
            startActivity(new Intent(Search.this, Edit.class));
            Toast.makeText(Search.this, "edit", Toast.LENGTH_SHORT).show();
        });

        home.setOnClickListener(v -> {
            startActivity(new Intent(Search.this, MainActivity.class));
            Toast.makeText(Search.this, "home", Toast.LENGTH_SHORT).show();
        });

        menu.setOnClickListener(v -> {
            startActivity(new Intent(Search.this, Menu.class));
            Toast.makeText(Search.this, "menu", Toast.LENGTH_SHORT).show();
        });
    }
}
