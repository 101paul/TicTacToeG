package com.example.tictactoeapp;

import android.widget.ImageView;

public class ImagesAndScores {
    int score;
    ImageView img;
    String name;
    String TAG="ImagesANDScores";
    ImagesAndScores(int score, ImageView img, String name) {
        this.score = score;
        this.img = img;
        this.name=name;
    }
}
