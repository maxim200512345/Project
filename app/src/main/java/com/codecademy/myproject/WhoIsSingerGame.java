package com.codecademy.myproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WhoIsSingerGame extends AppCompatActivity {
    //logic
    private MediaPlayer mPlayer;
    private AlertDialog myAlertDialog;
    private List<SongsItem> list;
    private Random r;
    private CountDownTimer timer;
    private static int limit = 2;
    private int count;
    private int turn = 1;
    //ui
    private TextView counter,timerView, btnText1,btnText2,btnText3,btnText4;
    private ImageButton btn1, btn2, btn3, btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        windowSettings();
        setContentView(R.layout.activity_classic_game);
        init();


        for(int i = 0; i < new Database().namesOfSongs.length; i++){
            list.add(new SongsItem(new Database().namesOfSongs[i], new Database().songs[i], new Database().authors[i]));
        }
        Collections.shuffle(list);

        newQuestion(turn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnText1.getText().toString().equalsIgnoreCase(list.get(turn-1).getAuthor())){
                    Toast.makeText(WhoIsSingerGame.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < limit){
                        clickCorrectSettings();

                    }
                    else{
                        mPlayer.stop();
                        showDialog(WhoIsSingerGame.this);
                        Toast.makeText(WhoIsSingerGame.this, "finish", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    clickFalseSettings();

                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnText2.getText().toString().equalsIgnoreCase(list.get(turn-1).getAuthor())){
                    Toast.makeText(WhoIsSingerGame.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < limit){
                        clickCorrectSettings();
                    }
                    else{
                        mPlayer.stop();
                        showDialog(WhoIsSingerGame.this);
                        Toast.makeText(WhoIsSingerGame.this, "finish", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    clickFalseSettings();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnText3.getText().toString().equalsIgnoreCase(list.get(turn-1).getAuthor())){
                    Toast.makeText(WhoIsSingerGame.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < limit){
                        clickCorrectSettings();
                    }
                    else{
                        mPlayer.stop();
                        showDialog(WhoIsSingerGame.this);
                        Toast.makeText(WhoIsSingerGame.this, "finish", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    clickFalseSettings();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnText4.getText().toString().equalsIgnoreCase(list.get(turn-1).getAuthor())){
                    Toast.makeText(WhoIsSingerGame.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < limit){
                        clickCorrectSettings();
                    }
                    else{
                        mPlayer.stop();
                        showDialog(WhoIsSingerGame.this);
                        Toast.makeText(WhoIsSingerGame.this, "finish", Toast.LENGTH_SHORT).show();

                    }
                }
                else{
                    clickFalseSettings();

                }
            }
        });

    }
    private void clickCorrectSettings(){
        turn++;
        count++;
        mPlayer.stop();
        newQuestion(turn);
        startTimer();
    }
    private void clickFalseSettings(){
        mPlayer.stop();
        Toast.makeText(WhoIsSingerGame.this, "wrong", Toast.LENGTH_SHORT).show();
        if (count >=0) count--;

        turn++;
        newQuestion(turn);
    }
    private void init(){
        r = new Random();
        btnText1 = findViewById(R.id.btntext1);
        btnText2 = findViewById(R.id.btntext2);
        btnText3 = findViewById(R.id.btntext3);
        btnText4 = findViewById(R.id.btntext4);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        counter = findViewById(R.id.result);
        timerView = findViewById(R.id.timerView);
        list = new ArrayList<>();
    }
    private void windowSettings(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void newQuestion(int number){
        //запуск песни
        int reference = list.get(number - 1).getRef();
        counter.setText("ваш результат - " + Integer.toString(count));
        startTimer();
        mPlayer= MediaPlayer.create(this, reference);
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mPlayer.setLooping(true);
                mPlayer.start();
            }
        });

        int correct_author = r.nextInt(4) + 1;

        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int fourthButton;

        switch (correct_author){
            case 1:
                btnText1.setText(list.get(firstButton).getAuthor());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText2.setText(list.get(secondButton).getAuthor());
                btnText3.setText(list.get(thirdButton).getAuthor());
                btnText4.setText(list.get(fourthButton).getAuthor());
                break;
            case 2:
                btnText2.setText(list.get(firstButton).getAuthor());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText1.setText(list.get(secondButton).getAuthor());
                btnText3.setText(list.get(thirdButton).getAuthor());
                btnText4.setText(list.get(fourthButton).getAuthor());
                break;
            case 3:
                btnText3.setText(list.get(firstButton).getAuthor());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText2.setText(list.get(secondButton).getAuthor());
                btnText1.setText(list.get(thirdButton).getAuthor());
                btnText4.setText(list.get(fourthButton).getAuthor());
                break;
            case 4:
                btnText4.setText(list.get(firstButton).getAuthor());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText2.setText(list.get(secondButton).getAuthor());
                btnText3.setText(list.get(thirdButton).getAuthor());
                btnText1.setText(list.get(fourthButton).getAuthor());
                break;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        mPlayer.stop();
    }

    public void showDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("игра окончена")
                .setMessage("отличный результат")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("ОК", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем окно
                        dialog.dismiss();
                        finish();

                    }
                });
        builder.show();

    }
    public  void startTimer(){
        if (timer == null){
            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerView.setText(Long.toString(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    turn++;
                    newQuestion(turn);
                }
            }.start();
        }
        else{
            timer.cancel();
            timer = new CountDownTimer(10000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerView.setText("осталось до конца раунда - " + Long.toString(millisUntilFinished));
                }

                @Override
                public void onFinish() {
                    //
                }
            }.start();
        }

    }


}