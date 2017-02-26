package com.example.android.udemygameconnectbuild14;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activeplayer=0;
    boolean gameIsactive=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{0,4,8},{2,4,6}};
    public void dropln(View view){
        // yellow=0,red=1

        ImageView counter=(ImageView)view;
        int tappedcounter=Integer.parseInt(counter.getTag().toString());
        counter.setTranslationY(-1000f);
       if(gamestate[tappedcounter]==2 &&gameIsactive){

           gamestate[tappedcounter]=activeplayer;
           if(activeplayer==0){
               counter.setImageResource(R.drawable.yellow);
               activeplayer=1;
           }
           else{
               counter.setImageResource(R.drawable.red);
               activeplayer=0;

           }
           counter.animate().translationYBy(1000f).rotation(3600).setDuration(300);
           for(int[] element :winningPositions){
               if(gamestate[element[0]]==gamestate[element[1]] &&
                  gamestate[element[1]]==gamestate[element[2]] &&
                  gamestate[element[0]]!=2){
                   TextView winnertext=(TextView)findViewById(R.id.textView);
                   String win="";
                   if(gamestate[element[0]]==0){
                       win="yellow win";
                   }else{
                       win="red win";
                   }
                   gameIsactive=false;
                   winnertext.setText(win);
                   LinearLayout layout=(LinearLayout)findViewById(R.id.linerlayoutnew);
                   layout.setVisibility(View.VISIBLE);
               }
           }
       }else{
           boolean gameIsOver=true;
           for(int counterState:gamestate){
               if(counterState==2)
                   gameIsOver =false;
           }
           if(gameIsOver){
               TextView winnertext=(TextView)findViewById(R.id.textView);
               String win="It's a draw";
               LinearLayout layout=(LinearLayout)findViewById(R.id.linerlayoutnew);
               layout.setVisibility(View.VISIBLE);
           }
       }


    }
    public void PlayAgain(View view){

        LinearLayout layout=(LinearLayout)findViewById(R.id.linerlayoutnew);
        layout.setVisibility(View.INVISIBLE);
        activeplayer=0;
        gameIsactive=true;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridlayoutnew);
        for (int i=0;i<gridLayout.getChildCount();i++){
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
