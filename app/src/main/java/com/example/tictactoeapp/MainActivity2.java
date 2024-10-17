package com.example.tictactoeapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
public class MainActivity2 extends AppCompatActivity {
        private ImageView b1, b2, b3, b4, b5, b6, b7, b8, b9;
        private ArrayList<ImageView> imagesArray;
        private HashMap<ImageView , Integer> boardMap;
        private int vacant=0, plyrId=1, sysId=2;
        private int turnId=plyrId;
        private int wins, losses, draws;
        private static final String TAG = "MyMainActivity";
        private TextView result , playername ;
        private ArrayList<ArrayList<ImageView>> allLines;
        private ArrayList<ImagesAndScores> rootsChildrenScores;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_single_pwith_aiopponent);
            this.initComponents();
        }
        private void initComponents(){

            boardMap = new HashMap<>();
            b1=findViewById(R.id.image1);
            b2=findViewById(R.id.image2);
            b3=findViewById(R.id.image3);
            b4=findViewById(R.id.image4);
            b5=findViewById(R.id.image5);
            b6=findViewById(R.id.image6);
            b7=findViewById(R.id.image7);
            b8=findViewById(R.id.image8);
            b9=findViewById(R.id.image9);


            playername = findViewById(R.id.player1name) ;
            playername.setText("You");
            result = findViewById(R.id.res) ;

            imagesArray=new ArrayList<>();
            rootsChildrenScores = new ArrayList<>();
            this.clearBoard();
            this.setListener();

            // Init map
            initMap();
            initLines();
            // Button
            Button rstBtn=findViewById(R.id.restart);

            rstBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    result.setText("");
                    clearBoard();
                }
            });
            Button qutBtn=(Button) findViewById(R.id.end);
            qutBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                    System.exit(0);
                }
            });
        }

        private void initMap(){
            boardMap.put(b1,0); boardMap.put(b2,0); boardMap.put(b3,0);
            boardMap.put(b4,0); boardMap.put(b5,0); boardMap.put(b6,0);
            boardMap.put(b7,0); boardMap.put(b8,0); boardMap.put(b9,0);
        }

        private void setListener() {

            for(ImageView im: imagesArray) {
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startGame(view);
                    }
                });
            }
        }


        private void initLines(){
            allLines= new ArrayList<>();
            ArrayList<ImageView> oneLine=new ArrayList<>();
            //line1 159,  line2 123, line3 147, line4 357, line5 789, line6 369, line7 258, line8 456
            oneLine.add(b1); oneLine.add(b5); oneLine.add(b9);
            allLines.add(oneLine);

            oneLine=new ArrayList<>();
            oneLine.add(b3); oneLine.add(b5); oneLine.add(b7);
            allLines.add(oneLine);

            oneLine=new ArrayList<>();
            oneLine.add(b4); oneLine.add(b5); oneLine.add(b6);
            allLines.add(oneLine);

            oneLine=new ArrayList<>();
            oneLine.add(b2); oneLine.add(b5); oneLine.add(b8);
            allLines.add(oneLine);

            oneLine=new ArrayList<>();
            oneLine.add(b1); oneLine.add(b2); oneLine.add(b3);
            allLines.add(oneLine);

            oneLine=new ArrayList<>();
            oneLine.add(b3); oneLine.add(b6); oneLine.add(b9);
            allLines.add(oneLine);

            oneLine=new ArrayList<>();
            oneLine.add(b7); oneLine.add(b8); oneLine.add(b9);
            allLines.add(oneLine);

            oneLine=new ArrayList<>();
            oneLine.add(b1); oneLine.add(b4); oneLine.add(b7);
            allLines.add(oneLine);

        }


        public boolean isGameOver() {
            //Game is over is someone has won, or board is full (draw)
            return (hasXWon() || hasOWon() || getAvailableStates().isEmpty());
        }

        private boolean hasXWon() {
            for (ArrayList<ImageView> line : allLines) {
                if (wonGame(line, plyrId)){
                    //           Log.d(TAG,"System might win!");
                    return true;
                }
            }
            //     Log.d(TAG,"X has not won yet");
            return false;
        }

        private boolean hasOWon() {
            for (ArrayList<ImageView> line : allLines) {
                if (wonGame(line, sysId)){
                    //           Log.d(TAG,"System might loose!");
                    return true;
                }
            }
            //   Log.d(TAG,"O has not won yet");
            return false;
        }



        private ArrayList<ImageView> getAvailableStates(){
            ArrayList<ImageView> availableStates=new ArrayList<>();
            for(ImageView im: imagesArray)
                if(boardMap.get(im)==vacant) {
//                Log.d(TAG,"Vacancy here!!!");
                    availableStates.add(im);
                }
            //     Log.d(TAG,"Vacancies: "+availableStates.size());
            return availableStates;
        }

        private void placeAMove(ImageView im){
            rootsChildrenScores.clear();
            markBox(im,sysId);
            //    Log.d(TAG,"Placing Move");
            updateImage(im, sysId);
            //    Log.d(TAG,"Image updated?: "+itos(im));
        }
        public ImageView returnBestMove() {
            int MAX = -100000;
            int best = -1;
            //      Log.d(TAG,"Return best: size of roots: "+rootsChildrenScores.size());
            for (int i = 0; i < rootsChildrenScores.size(); ++i) {
                //          Log.d(TAG,"Loop score: "+ rootsChildrenScores.get(i).score);
                if (MAX < rootsChildrenScores.get(i).score) {
                    MAX = rootsChildrenScores.get(i).score;
                    best = i;
                }
            }
            //    Log.d(TAG, "Inside return best Move! with best "+best);
            return rootsChildrenScores.get(best).img;
        }
        public int returnMin(List<Integer> list) {
            int min = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i) < min) {
                    min = list.get(i);
                    index = i;
                }
            }
            return list.get(index);
        }
        public int returnMax(List<Integer> list) {
            int max = Integer.MIN_VALUE;
            int index = -1;
            for (int i = 0; i < list.size(); ++i) {
                if (list.get(i) > max) {
                    max = list.get(i);
                    index = i;
                }
            }
            return list.get(index);
        }

        public void callMinimax(int depth, int turn){
            //       Log.d(TAG,"Inside callMiniMax ");
            minimax(depth, turn);
        }
        public int minimax(int depth, int turn) {
            //      Log.d(TAG,"Inside miniMax");
            if (hasOWon()) return +1;
            if (hasXWon()) return -1;

            ArrayList<ImageView> imagesAvailable = getAvailableStates();
//        Log.d(TAG,"Size of imagesAvailable: "+imagesAvailable.size());
            if (imagesAvailable.isEmpty()) return 0;

            ArrayList<Integer> scores = new ArrayList<>();
            for (int i = 0; i < imagesAvailable.size(); ++i) {

                ImageView im = imagesAvailable.get(i);
                if (turn == sysId) { //O's turn select the highest from below minimax() call
                    if(boardMap.get(im)==vacant) boardMap.put(im, sysId);
                    int currentScore = minimax(depth + 1, plyrId);
                    //              Log.d(TAG,"CurrentScore: "+currentScore);
                    scores.add(currentScore);
                    if (depth == 0) {
//                    Log.d(TAG,"Adding to Roots Children Scores");
                        rootsChildrenScores.add(new ImagesAndScores(currentScore, im, itos(im)));
                    }
                } else if (turn == plyrId) {//X's turn select the lowest from below minimax() call
                    if(boardMap.get(im)==vacant) boardMap.put(im, plyrId);
                    scores.add(minimax(depth + 1, sysId));
                }
                boardMap.put(im, vacant); //Reset this point
            }
//        Log.d(TAG,"Min Score: "+returnMin(scores)+"MaxScores: "+returnMax(scores));
            return turn == sysId ? returnMax(scores) : returnMin(scores);
        }

        private void whoWon(){
            if(isGameOver()){
                if(hasOWon()){
                    result.setText("Ai Won!");
                }
                else if(hasXWon()){
                    result.setText("You Won!");
                }
                else if(isFull()){
                    result.setText("Draw!");
                }
            }
        }

        private void startGame(View v){
            if(isGameOver()){
                return;
            }
            //       Log.d(TAG, "Clicked on ImageView!");
            Random rand = new Random();
            if(isFull()){
                //          Log.d(TAG,"Board is full");
                return;
            }

            if(turnId==plyrId){
                //        Log.d(TAG,"It's players Turn");
                if(tryMarking(v))
                    startGame(v);
                return;
            }

            if(turnId==sysId & isEmpty()){
                //        Log.d(TAG,"System will start");
                ImageView im = imagesArray.get(rand.nextInt(10));
                placeAMove(im);
                this.turnId=plyrId;
                return;
            }
            else if(turnId==sysId & !isEmpty() ){
                //         Log.d(TAG,"System's Turn");
                callMinimax(0, sysId);
                placeAMove(returnBestMove());
                this.turnId=plyrId;
                return;
            }
        }

        private boolean isEmpty(){

            for(ImageView im: imagesArray)
                if(boardMap.get(im)!=vacant)
                    return false;
            return true;
        }
        private boolean isNotMarked(ImageView tv){
            return boardMap.get(tv)==vacant;
        }

        //Mark the box with the specified turnId

        private void markBox(ImageView tv, int turnId){
            boardMap.put(tv,turnId);
//        Log.d(TAG, "Marked and updated!");
            whoWon();
        }

        //Try marking box

        private boolean tryMarking(View v){
            // get the tv who invoked the method.
            ImageView  calledTv= (ImageView)findViewById(v.getId());
            if(isNotMarked(calledTv) & turnId==plyrId){
                markBox(calledTv, turnId);
                updateImage(calledTv, plyrId);
                this.turnId=sysId;
                return true;
            }
            return false;
        }


        // Update the mark

        private void updateImage(ImageView img, int turnId){
            if(turnId==plyrId) img.setImageResource(R.drawable.cross2);
            else img.setImageResource(R.drawable.o2);
        }



        private boolean isFull() {
            for (ImageView img : imagesArray) {
                int id = boardMap.get(img);
                if (id == vacant) return false;
            }
            return true;
        }

        private boolean wonGame(ArrayList<ImageView> curLine, int turnId){
            ImageView v1= curLine.get(0);
            ImageView v2= curLine.get(1);
            ImageView v3= curLine.get(2);
            int id1=boardMap.get(v1);
            int id2=boardMap.get(v2);
            int id3=boardMap.get(v3);
            if(id1==turnId & id2==turnId & id3==turnId) return true;
            return false;
        }

        // If the over clear the array once restart button is clicked
        private void clearBoard(){
            // ArrayList of ImageViews
            imagesArray=new ArrayList<>();
            imagesArray.add(b1); imagesArray.add(b2); imagesArray.add(b3);
            imagesArray.add(b4); imagesArray.add(b5); imagesArray.add(b6);
            imagesArray.add(b7); imagesArray.add(b8); imagesArray.add(b9);
            rootsChildrenScores.clear();
            for(ImageView img: imagesArray){
                img.setImageResource(R.drawable.round_dark_grey);
            }
            initMap();
            initLines();
        }


        private String itos(ImageView im){
            if(im.equals(b1)) return "b1";
            else if(im.equals(b2)) return "b2";
            else if(im.equals(b3)) return "b3";
            else if(im.equals(b4)) return "b4";
            else if(im.equals(b5)) return "b5";
            else if(im.equals(b6)) return "b6";
            else if(im.equals(b7)) return "b7";
            else if(im.equals(b8)) return "b8";
            else if(im.equals(b9)) return "b9";
            return "None";
        }
}
