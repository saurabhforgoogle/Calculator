package com.example.calculator;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ActionBar actionBar;
        actionBar=getSupportActionBar();
        ColorDrawable colorDrawable=new ColorDrawable(Color.parseColor("#FF000000"));
        actionBar.setBackgroundDrawable(colorDrawable);
        Intent intent=getIntent();
        ArrayList<String> InputText=intent.getStringArrayListExtra("inputText");
        ArrayList<String> OutputText=intent.getStringArrayListExtra("outputText");
        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemAdapter adapter=new ItemAdapter(InputText,OutputText);
        recyclerView.setAdapter(adapter);


    }

    public void calcfunc(View view) {
        Intent intent3=new Intent(this,MainActivity.class);

        /*intent3.putStringArrayListExtra("inputText",InputText);
        intent3.putStringArrayListExtra("outputText", OutputText);*/

        startActivity(intent3);
    }

    public void lifefunc(View view) {
        Intent intent4=new Intent(this,life.class);
        startActivity(intent4);
    }
}