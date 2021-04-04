package com.example.userlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Setting extends AppCompatActivity {

    private MyOpenHelper helper ;
    String kbn = "";

    String toastMessage = "登録しました。戻るを押してください";
    String toastMessage2 = "ユーザデータを入力してください";
    String toastMessage3 = "更新しました。戻るを押してください";
    String toastMessage4 = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //DB作成
        helper = new MyOpenHelper(getApplicationContext());

        //データを受け取る
        Intent intent = getIntent();
        String KBN = intent.getStringExtra("KBN");

        Button button = findViewById(R.id.button);
        View view = findViewById(R.id.Layout);

        if(KBN.length() != 0){

        }else{
            //新規登録
            kbn = "登録";

            //ボタンテキスト変更
            button.setText("登録");

            //背景色変更
            view.setBackgroundColor(Color.CYAN);
        }
    }

    /**
    ユーザデータを保存する
    * @param view
    */
    public void saveData(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();

        EditText txt1 = findViewById(R.id.userName);
        EditText txt2 = findViewById(R.id.userId);
        EditText txt3 = findViewById(R.id.userPass);

        String name = txt1.getText().toString();
        String ID = txt2.getText().toString();
        String PS = txt3.getText().toString();

        ContentValues values = new ContentValues();
        values.put("name" , name);
        values.put("ID" , ID);
        values.put("pass" , PS);

        //ボタンが登録の場合
        if(kbn == "登録") {
            if (name.length() != 0) {
                //新規登録
                db.insert("myPasstb", null, values);
                //トースト表示
                toastMake(toastMessage, 0, +350);
            } else {
                //トースト表示
                toastMake(toastMessage2, 0, +350);
            }

            //ボタンが更新の場合
        }else{
            if(name.length() != 0){
                //更新
                UPData(kbn);
                //トースト表示
                toastMake(toastMessage3, 0, +400);
            }else{
                toastMake(toastMessage4, 0 , +400);
            }
        }
    }

    /**
     * データ更新
     * @param read
     */
    public void UPData(String read){
        SQLiteDatabase db = helper.getReadableDatabase();

        EditText txt1 = findViewById(R.id.userName);
        EditText txt2 = findViewById(R.id.userId);
        EditText txt3 = findViewById(R.id.userPass);

        String name = txt1.getText().toString();
        String ID = txt2.getText().toString();
        String PS = txt3.getText().toString();

        ContentValues upValue = new ContentValues();
        upValue.put("name",name);
        upValue.put("ID",ID);
        upValue.put("pass",PS);

        db.update("myPasstb",upValue,"_id=?",new String[]{read});
    }

    private void toastMake(String message, int x, int y){
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        // 位置調整
        toast.setGravity(Gravity.CENTER, x, y);
        toast.show();
    }

    //画面を閉じる
    public void onClose(View view) {
        finish();
    }
}