package com.example.tictactoeapp;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class add1player extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add1player);
        Button btn  =findViewById(R.id.startGameBtn) ;

        btn.setOnClickListener(view -> {
//                   Log.v("button","button working properly") ;
            Intent intent = new Intent(add1player.this,MainActivity2.class) ;
            startActivity(intent) ;

        });

    }
}