package com.example.tictactoeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<int[]> combinationList = new ArrayList<>() ;
    private int[] boxPosition = {0,0,0,0,0,0,0,0,0} ;
    private int playerTurn = 1 ;
    private int totalselectedBoxes = 1  ;
    private TextView  result ;
    private Button restart , end ;
    private ImageView img1 , img2 , img3 , img4 , img5 , img6 , img7 , img8 , img9 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         restart = findViewById(R.id.restart) ;
         end = findViewById(R.id.end)  ;
         result = findViewById(R.id.res) ;

         img1 = findViewById(R.id.image1) ;
         img2 = findViewById(R.id.image2) ;
         img3 = findViewById(R.id.image3) ;
         img4 = findViewById(R.id.image4) ;
         img5 = findViewById(R.id.image5) ;
         img6 = findViewById(R.id.image6) ;
         img7 = findViewById(R.id.image7) ;
         img8=  findViewById(R.id.image8) ;
         img9=  findViewById(R.id.image9) ;

         combinationList.add(new int[]{0,1,2}) ;
         combinationList.add(new int[]{3,4,5}) ;
         combinationList.add(new int[]{6,7,8}) ;
         combinationList.add(new int[]{0,3,6}) ;
         combinationList.add(new int[]{1,4,7}) ;
         combinationList.add(new int[]{2,5,8}) ;
         combinationList.add(new int[]{2,4,6}) ;
         combinationList.add(new int[]{0,4,8}) ;





         restart.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 result.setText("");
                 restartmatch();
             }
         });

         end.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 finish();
                 System.exit(0);
             }
         });

         img1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                if(isBoxSelectable(0)){
                      performAction((ImageView)view,0);
                 }
             }
         });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(1)){
                    performAction((ImageView)view,1);

                }
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(2)){
                    performAction((ImageView)view,2);

                }
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(3)){
                    performAction((ImageView)view,3);

                }
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(4)){
                    performAction((ImageView)view,4);

                }
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(5)){
                    performAction((ImageView)view,5);

                }
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(6)){
                    performAction((ImageView)view,6);

                }
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(7)){
                    performAction((ImageView)view,7);

                }
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isBoxSelectable(8)){
                    performAction((ImageView)view,8);
                }
            }
        });
    }
    private void performAction(ImageView imageview , int selectedBoxPosition) {
        boxPosition[selectedBoxPosition] = playerTurn;
        if (playerTurn == 1) {
            imageview.setImageResource(R.drawable.cross2);
            if (checkPlayerWin()) {
                result.setText("X Won!");

            }
            else if(totalselectedBoxes == 9){
                result.setText("Draw!");
            }
            else{
                changePlayerTurn(2);
                totalselectedBoxes++ ;
            }
        }
        else{
            imageview.setImageResource(R.drawable.o2) ;
            if(checkPlayerWin()){
                result.setText("O Won!");
            }
            else if(totalselectedBoxes == 9){
                result.setText("Draw!");
            }
            else{
                changePlayerTurn(1);
                totalselectedBoxes++ ;
            }
        }
    }
    private void changePlayerTurn(int currentPlayerTurn){
        playerTurn = currentPlayerTurn ;

    }
    private boolean checkPlayerWin(){
        boolean response = false ;
        for(int i = 0 ; i<combinationList.size() ; i++){
            final int[] combination = combinationList.get(i) ;
            if(boxPosition[combination[0]] == playerTurn && boxPosition[combination[1]] == playerTurn && boxPosition[combination[2]]==playerTurn){
                response = true ;
            }
        }
        return response ;
    }
    private boolean isBoxSelectable(int boxposition){
        boolean response ;
        if(boxPosition[boxposition]==0){
            response = true ;
            return response ;
        }
        else{
            response = false ;
            return response ;
        }
    }
    public void restartmatch(){
        boxPosition = new int[]{0,0,0,0,0,0,0,0,0} ;
        playerTurn = 1 ;
        totalselectedBoxes = 0 ;
        ((ImageView) findViewById(R.id.image1)).setImageResource(0);
        ((ImageView) findViewById(R.id.image2)).setImageResource(0);
        ((ImageView) findViewById(R.id.image3)).setImageResource(0);
        ((ImageView) findViewById(R.id.image4)).setImageResource(0);
        ((ImageView) findViewById(R.id.image5)).setImageResource(0);
        ((ImageView) findViewById(R.id.image6)).setImageResource(0);
        ((ImageView) findViewById(R.id.image7)).setImageResource(0);
        ((ImageView) findViewById(R.id.image8)).setImageResource(0);
        ((ImageView) findViewById(R.id.image9)).setImageResource(0);
    }
    public void exit(ImageView imageview){
        this.finishAffinity();
    }

}