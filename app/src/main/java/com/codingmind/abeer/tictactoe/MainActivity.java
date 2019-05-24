package com.codingmind.abeer.tictactoe;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private int roundCount;
    private int activityCount = 1;

    private boolean player1turn = true;

    private int player1points;
    private int player2points;

    private TextView textviewPlayer1;
    private TextView textviewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewPlayer1 = findViewById(R.id.textview_p1);
        textviewPlayer2 = findViewById(R.id.textview_p2);

        textviewPlayer1.setBackgroundColor(Color.rgb(220,20,60));

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                String buttonID = "button_"+i+j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        ImageButton resetButton = findViewById(R.id.resetButton_id);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });

        Button clearButton = findViewById(R.id.clearBoardID);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")){
            return;
        }

        if (player1turn){
            ((Button) v).setText("X");

        }else {
            ((Button) v).setText("O");

        }

        roundCount++;
        activityStatus();

        if (checkForWin()){
            if (player1turn){
                player1wins();
            }else{
                player2wins();
            }
        }else if (roundCount == 9){
            draw();
        }else {
            player1turn = !player1turn;
        }
    }

    public boolean checkForWin(){
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")){
                return true;
            }
        }

        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

    public void player1wins(){
        player1points++;
        Toast.makeText(this, "Player 1 wins", Toast.LENGTH_SHORT).show();
        updatePointsTable();
        resetBoard();
    }

    public void player2wins(){
        player2points++;
        Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
        updatePointsTable();
        resetBoard();
    }

    public void draw(){
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    public void updatePointsTable(){
        textviewPlayer1.setText("Player 1: "+player1points);
        textviewPlayer2.setText("Player 2: "+player2points);
    }

    public void resetBoard(){
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        activityCount = 1;
        activityNum();
        player1turn = true;
    }

    public void resetGame(){
        resetBoard();
        player1points = 0;
        player2points = 0;
        updatePointsTable();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("Player1Points",player1points);
        outState.putInt("Player2Points",player2points);
        outState.putInt("RoundCount", roundCount);
        outState.putBoolean("Player1Turn", player1turn);
        outState.putInt("ActivityCount", activityCount);

        activityNum();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        player1points = savedInstanceState.getInt("Player1Points");
        player2points = savedInstanceState.getInt("Player2Points");
        roundCount = savedInstanceState.getInt("Player1Turn");
        player1turn = savedInstanceState.getBoolean("Player1Turn");
        activityCount = savedInstanceState.getInt("ActivityCount");

        activityNum();
    }

    public void activityStatus(){
        activityCount++;

        if (activityCount%2 == 0){
            textviewPlayer1.setBackgroundColor(Color.rgb(123, 123, 123));
            textviewPlayer2.setBackgroundColor(Color.rgb(220,20,60));
        }else {
            textviewPlayer1.setBackgroundColor(Color.rgb(220,20,60));
            textviewPlayer2.setBackgroundColor(Color.rgb(123, 123, 123));
        }
    }

    public void activityNum(){
        if (activityCount%2 == 0){
            textviewPlayer1.setBackgroundColor(Color.rgb(123, 123, 123));
            textviewPlayer2.setBackgroundColor(Color.rgb(220,20,60));
        }else {
            textviewPlayer1.setBackgroundColor(Color.rgb(220,20,60));
            textviewPlayer2.setBackgroundColor(Color.rgb(123, 123, 123));
        }
    }

}




































