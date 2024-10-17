package com.example.tictactoeapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class winDialog extends Dialog {
    private final String message ;
    private final MainActivity mainActivity ;
    public winDialog(@NonNull Context context , String message , MainActivity mainActivity) {
        super(context);
        this.message = message ;
        this.mainActivity = mainActivity ;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.win_dialog_layout);

        TextView messageText = findViewById(R.id.messagatext) ;
        Button startAgainBtn = findViewById(R.id.startagainbtn) ;
        Button exitbtn = findViewById(R.id.exitbtn) ;
        messageText.setText(message) ;
        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mainActivity.restartmatch();
              dismiss() ;
            }
        });
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss() ;
            }
        });
    }
}
