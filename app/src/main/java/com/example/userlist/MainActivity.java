package com.example.userlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //登録ボタン押下時に新規登録画面に遷移
    public void Register(View view) {
        Intent intent = new Intent(getApplication(),Setting.class);

        //モード指定 空は新規
        intent.putExtra("KBN","");

        //
        startActivity(intent);
    }
}