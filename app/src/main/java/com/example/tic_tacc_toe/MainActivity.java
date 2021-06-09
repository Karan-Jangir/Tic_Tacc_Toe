package com.example.tic_tacc_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0=x 1=o
    int activeplayer=0;
    boolean gameisActive=true;
    //2 means unplayed
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view) {
        ImageView o = (ImageView) view;

        int tappedcounter = Integer.parseInt(o.getTag().toString());

        if (gamestate[tappedcounter] == 2) {
            gamestate[tappedcounter]=activeplayer;
            o.setTranslationY(-1000f);
            if (activeplayer == 0) {
                o.setImageResource(R.drawable.xx);
                activeplayer = 1;
            } else {
                o.setImageResource(R.drawable.oo);
                activeplayer = 0;
            }
            o.animate().translationYBy(1000f).setDuration(200);
            for(int[] winningposition: winningpositions)
            {
                if(gamestate[winningposition[0]]==gamestate[winningposition[1]] &&
                gamestate[winningposition[1]]==gamestate[winningposition[2]] &&
                gamestate[winningposition[0]] != 2)
                {
                    gameisActive=false;
                 //   System.out.println(gamestate[winningposition[0]]);
                   String winner="O";
                   if(gamestate[winningposition[0]]==0)
                   {
                        winner="X";
                   }
                    //someone has won
                    TextView winnermessage=findViewById(R.id.winner_message);
                    winnermessage.setText(winner+" has won!!");
                    LinearLayout layout=findViewById(R.id.playagain_layout);
                    layout.setVisibility(View.VISIBLE);
                }
                else
                {
                    boolean gameisOver=true;
                    for(int counterstate:gamestate)
                    {
                        if(counterstate==2)
                            gameisOver=false;
                    }
                    if(gameisOver)
                    {
                        TextView winnermesssage=findViewById(R.id.winner_message);
                        winnermesssage.setText("It is a draw!");
                        LinearLayout layout = findViewById(R.id.playagain_layout);
                        layout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }
    public void playAgain(View view)
    {
        gameisActive=true;
        LinearLayout layout=findViewById(R.id.playagain_layout);
        layout.setVisibility(View.INVISIBLE);
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        GridLayout gridLayout=findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}