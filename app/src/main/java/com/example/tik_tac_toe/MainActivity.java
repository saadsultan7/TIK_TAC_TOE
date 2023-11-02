package com.example.tik_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    Button Reset;
    int active_player = 0;
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] win_positions = {{0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};
    int xwins = 1;
    int owins = 1;
    boolean gameactive = true;
    TextView xscore;
    TextView oscore;
    MediaPlayer player;
    @SuppressLint("SetTextI18n")
    public void player_tap(View view)
    {
        Reset = findViewById(R.id.Reset);
        player = MediaPlayer.create(this,R.raw.balsound);
        player.start();
        xscore = findViewById(R.id.xscore);
        oscore = findViewById(R.id.oscore);
        TextView textView2 = findViewById(R.id.textView2);
        ImageView img = (ImageView)view;
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setText("X - Turn");
                restart(view);
            }
        });
        int tapped_image = Integer.parseInt(img.getTag().toString());
        if(!gameactive){
            restart(view);
        }
        if(gamestate[tapped_image] == 2)
        {
            gamestate[tapped_image] = active_player; //this will change the gamestate (2) to the provided active player
            img.setTranslationX(-1000f);
            img.animate().translationXBy(1000f).setDuration(200);
            if(active_player == 0)
            {
                img.setImageResource(R.drawable.cross);
                active_player = 1;
                textView2.setText("O - Turn");
            }
            else
            {
                img.setImageResource(R.drawable.circle);
                active_player = 0;
                textView2.setText("X - Turn");
            }
            for (int [] win_position:win_positions)
            {
                if(gamestate[win_position[0]] == gamestate[win_position[1]] &&
                        gamestate[win_position[1]] == gamestate[win_position[2]]&&
                        gamestate[win_position[0]]!=2)
                {
                    gameactive = false;
                    if(gamestate[win_position[0]]==0){
                        textView2.setText("X is the Winner");
                        xscore.setText("X-Wins:"+xwins);
                        xwins++;
                    }
                    else{
                        textView2.setText("O is the Winner");
                        oscore.setText("O-Wins:"+owins);
                        owins++;
                    }
                }
            }
        }
        boolean emptySquare = false;
        for (int squareState : gamestate) {
            if (squareState == 2) {
                emptySquare = true;
                break;
            }
        }
        if (!emptySquare && gameactive) {
            // Game is a draw
            gameactive = false;
            textView2.setText("its a draw");
        }
    }
    public void restart(View view)
    {
        gameactive = true;
        active_player = 0;
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        Arrays.fill(gamestate, 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
