package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView playerTurn;
    private String player;
    private Button topLeftCmd;
    private Button topMiddleCmd;
    private Button topRightCmd;
    private Button middleLeftCmd;
    private Button centerCmd;
    private Button middleRightCmd;
    private Button bottomLeftCmd;
    private Button bottomCenterCmd;
    private Button bottomRightCmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerTurn = findViewById(R.id.playersTurn);
        playerTurn.setText("Player X's Turn");
        player = "X";

        topLeftCmd = findViewById(R.id.topLeftCmd);
        topMiddleCmd = findViewById(R.id.topMiddleCmd);
        topRightCmd = findViewById(R.id.topRightCmd);
        middleLeftCmd = findViewById(R.id.middleLeftCmd);
        centerCmd = findViewById(R.id.centerCmd);
        middleRightCmd = findViewById(R.id.middleRightCmd);
        bottomLeftCmd = findViewById(R.id.bottomLeftCmd);
        bottomCenterCmd = findViewById(R.id.bottomCenterCmd);
        bottomRightCmd = findViewById(R.id.bottomRightCmd);
    }

    public void newGameClick(View v) {

        Button[] list = new Button[]{topLeftCmd, topMiddleCmd, topRightCmd, middleLeftCmd,
                centerCmd, middleRightCmd, bottomLeftCmd, bottomCenterCmd, bottomRightCmd};

        for(int i = 0; i < list.length; i++) {
            list[i].setClickable(true);
            list[i].setText("");
        }

        playerTurn.setText("Player X's Turn");
        player = "X";
    }

    public void gameWon(View v) {

        Button[] list = new Button[]{topLeftCmd, topMiddleCmd, topRightCmd, middleLeftCmd,
                centerCmd, middleRightCmd, bottomLeftCmd, bottomCenterCmd, bottomRightCmd};
        int count = 0;

        if(playerWonGame()) {
            for (int i = 0; i < list.length; i++) {
                if(list[i].getText().equals("")) {
                    list[i].setClickable(false);
                }
            }
        }
        else {
            for (int i = 0; i < list.length; i++) {
                if(!list[i].getText().equals("")) {
                    count++;
                    if(count >= 9) {
                        playerTurn.setText("Game is a draw");
                        count = 0;
                    }
                }
            }

        }
    }

    public void turnSubmitted(View v) {
        Button buttonClicked = findViewById(v.getId());

        if(player.equals("X")) {
            buttonClicked.setText("X");
            buttonClicked.setClickable(false);
            playerTurn.setText("Player O's Turn");
            player = "O";
        }
        else if(player.equals("O")) {
            buttonClicked.setText("O");
            buttonClicked.setClickable(false);
            playerTurn.setText("Player X's Turn");
            player = "X";
        }

        gameWon(v);
    }

    public boolean playerWonGame() {
        Button[][] list = new Button[][] {{topLeftCmd, topMiddleCmd, topRightCmd},
                                          {middleLeftCmd, centerCmd, middleRightCmd},
                                          {bottomLeftCmd, bottomCenterCmd, bottomRightCmd}};
        //TicTacToe Horizontal row
        for(int i = 0; i < list.length; i++) {
            for(int j = 0; j < list[i].length - 2; j++) {
                if(list[i][j].getText().equals("X") || list[i][j].getText().equals("O")) {
                    if (list[i][j].getText().equals(list[i][j + 1].getText()) &&
                            list[i][j].getText().equals(list[i][j + 2].getText())) {
                        playerTurn.setText("Player " + list[i][j].getText() + " has Won");
                        return true;
                    }
                }
            }
        }
        //TicTacToe Vertical row
        for(int i = 0; i < list.length - 2; i++) {
            for (int j = 0; j < list[i].length; j++) {
                if (list[i][j].getText().equals("X") || list[i][j].getText().equals("O")) {
                    if (list[i][j].getText().equals(list[i + 1][j].getText()) &&
                            list[i][j].getText().equals(list[i + 2][j].getText())) {
                        playerTurn.setText("Player " + list[i][j].getText() + " has Won");
                        return true;
                    }
                }
            }
        }
        //TicTacToe Down Diagonal row
        for(int i = 0; i < list.length - 2; i++) {
            for (int j = 0; j < list[i].length - 2; j++) {
                if (list[i][j].getText().equals("X") || list[i][j].getText().equals("O")) {
                    if (list[i][j].getText().equals(list[i + 1][j + 1].getText()) &&
                            list[i][j].getText().equals(list[i + 2][j + 2].getText())) {
                        playerTurn.setText("Player " + list[i][j].getText() + " has Won");
                        return true;
                    }
                }
            }
        }
        //TicTacToe Up Diagonal row
        for(int i = 0; i < list.length - 2; i++) {
            for (int j = 0; j < list[i].length - 2; j++) {
                if (list[i + 2][j].getText().equals("X") || list[i + 2][j].getText().equals("O")) {
                    if (list[i + 2][j].getText().equals(list[i + 1][j + 1].getText()) &&
                            list[i + 2][j].getText().equals(list[i][j + 2].getText())) {
                        playerTurn.setText("Player " + list[i + 2][j].getText() + " has Won");
                        return true;
                    }
                }
            }
        }

        return false;
    }
}