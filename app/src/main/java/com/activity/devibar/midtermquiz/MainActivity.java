package com.activity.devibar.midtermquiz;

import android.graphics.Color;
import android.icu.text.TimeZoneFormat;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mScore;
    private TextView mColor;
    private TextView mColorName;
    private TextView mTimer;
    private Switch mPlay;
    private Button mTrue;
    private Button mFalse;
    Thread thread;
    Handler handler;
    int score = 0;
    String colorName1="";
    String colorName2="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScore = (TextView) findViewById(R.id.txtScore);
        mColor = (TextView) findViewById(R.id.txtColor);
        mColorName = (TextView) findViewById(R.id.txtColorName);
        mTimer = (TextView)findViewById(R.id.txtTimer);
        mPlay = (Switch) findViewById(R.id.swPlay);
        mTrue = (Button) findViewById(R.id.btnTrue);
        mFalse = (Button) findViewById(R.id.btnFalse);


        mTrue.setOnClickListener(this);
        mFalse.setOnClickListener(this);
        mPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked){
                    mTrue.setEnabled(true);
                    mFalse.setEnabled(true);
                    thread = new Thread(new TimerThread());
                    thread.start();
                    randomColors();
                    randomColorNames();
                }
                else {
                    mTrue.setEnabled(false);
                    mFalse.setEnabled(false);
                }

            }
        });

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                mTimer.setText(msg.arg1+"");
            }
        };



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnTrue:
                if (check()) {
                    score++;
                }
                else {
                    score--;
                }
                break;
            case R.id.btnFalse:
                if (check()) {
                    score--;
                }
                else {
                    score++;
                }
                break;
            default: break;
        }
        mScore.setText("Score: "+score);
        randomColors();
        randomColorNames();

        thread = new Thread(new TimerThread());
        thread.start();
    }

    public void randomColors(){

        Random rand = new Random();
        int color = rand.nextInt(5) + 1;
        switch (color){
            case 1: mColor.setBackgroundColor(Color.RED);
                    colorName1 = "RED";
                break;
            case 2: mColor.setBackgroundColor(Color.MAGENTA);
                    colorName1 = "MAGENTA";
                break;
            case 3: mColor.setBackgroundColor(Color.YELLOW);
                colorName1 = "YELLOW";
                break;
            case 4: mColor.setBackgroundColor(Color.BLUE);
                colorName1 = "BLUE";
                break;
            case 5: mColor.setBackgroundColor(Color.GREEN);
                colorName1 = "GREEN";
                break;
        }

    }

    public void randomColorNames(){
        Random rand = new Random();
        int color = rand.nextInt(5) + 1;
        switch (color){
            case 1: mColorName.setText("RED");
                colorName2 = "RED";
                break;
            case 2: mColorName.setText("MAGENTA");
                colorName2 = "MAGENTA";
                break;
            case 3: mColorName.setText("YELLOW");
                colorName2 = "YELLOW";
                break;
            case 4: mColorName.setText("BLUE");
                colorName2 = "BLUE";
                break;
            case 5: mColorName.setText("GREEN");
                colorName2 = "GREEN";
                break;
        }

    }

    public boolean check(){
        if (colorName1.equals(colorName2)){
            return true;
        }
        else {
            return false;
        }
    }







    class TimerThread implements Runnable{


        @Override
        public void run() {

                for (int i=0; i<11; i++){
                        Message message = (new Message()).obtain();
                        message.arg1=i;
                        handler.sendMessage(message);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                        }

                    }
                }
            }

            }




