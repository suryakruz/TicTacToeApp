package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.VISIBLE;

public class MainActivity extends AppCompatActivity
{
    // 1 represents X , 0 represents 0
    int activeGamer = 0;
    boolean isGameActive = true;
    Map<String, Boolean> assignedMap = new HashMap<>();
    int[] gameStates = { 2, 2, 2, 2, 2, 2, 2, 2, 2 };
    int[][] winningRule = { {0,1,2}, {3,4,5}, {6,7,8}, {0,4,8}, {2,4,6}, {0,3,6}, {1,4,7}, {2,5,8}};

    public void reset( View view )
    {
        activeGamer = 0;
        assignedMap = new HashMap<>();
        Arrays.fill(gameStates, 2);

        TextView textView1 = this.findViewById( R.id.box1 );
        textView1.setText("");
        TextView textView2 = this.findViewById( R.id.box2 );
        textView2.setText("");
        TextView textView3 = this.findViewById( R.id.box3 );
        textView3.setText("");
        TextView textView4 = this.findViewById( R.id.box4 );
        textView4.setText("");
        TextView textView5 = this.findViewById( R.id.box5 );
        textView5.setText("");
        TextView textView6 = this.findViewById( R.id.box6 );
        textView6.setText("");
        TextView textView7 = this.findViewById( R.id.box7 );
        textView7.setText("");
        TextView textView8 = this.findViewById( R.id.box8 );
        textView8.setText("");
        TextView textView9 = this.findViewById( R.id.box9 );
        textView9.setText("");

        Button button1 = this.findViewById( R.id.button2 );
        button1.setVisibility( View.INVISIBLE );
        Button button2 = this.findViewById( R.id.button3 );
        button2.setVisibility( View.INVISIBLE );
        this.isGameActive = true;
    }

    public void onClickBoard( View view )
    {
        TextView textView = this.findViewById( view.getId() );
        if ( assignedMap.get(String.valueOf( textView.getId() )) == null && isGameActive )
        {
            if ( activeGamer == 0 ) {
                textView.setText("X");
                gameStates[Integer.parseInt( (String) textView.getTag() )] = 1;
                activeGamer = 1;
            }
            else
            {
                textView.setText( "0" );
                gameStates[Integer.parseInt( (String) textView.getTag() )] = 0;
                activeGamer = 0;
            }
            assignedMap.put( String.valueOf( textView.getId() ), Boolean.TRUE );
            boolean isMatchWon = false;
            for( int[] rule : winningRule )
            {
                int i = 0;
                if( gameStates[rule[i]] ==  gameStates[rule[i+1]]
                        && gameStates[rule[i+1]] == gameStates[rule[i+2]]
                        && gameStates[rule[i]] != 2 )
                {
                    this.isGameActive = false;
                    Button button = this.findViewById( R.id.button2 );
                    button.setVisibility( VISIBLE );
                    button.setTextColor(Color.parseColor( "#ffff00") );
                    button.setText(new StringBuilder().append(textView.getText()).append(" has won ").toString());
                    isMatchWon = true;

                    Button button1 = this.findViewById( R.id.button3 );
                    button1.setVisibility( VISIBLE );
                    button1.setTextColor(Color.parseColor( "#ffff00") );
                    button1.setText( "Play Again");
                }
            }

            if( !isMatchWon ) {
                int count = 0;
                for (int gameState : gameStates) {
                    if (gameState != 2) {
                        count++;
                    }
                }

                if (count == 9) {
                    Button button = this.findViewById(R.id.button2);
                    button.setVisibility(VISIBLE);
                    button.setTextColor(Color.parseColor("#ffff00"));
                    button.setText("Match drawn");

                    Button button1 = this.findViewById( R.id.button3 );
                    button1.setVisibility( VISIBLE );
                    button1.setTextColor(Color.parseColor( "#ffff00") );
                    button1.setText( "Play Again");
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}