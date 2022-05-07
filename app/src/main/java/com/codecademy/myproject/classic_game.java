package com.codecademy.myproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class classic_game extends AppCompatActivity {
    //ui
    private ImageButton btn1, btn2, btn3, btn4;
    private TextView counter, rd, btnText1,btnText2,btnText3,btnText4, timerView;
    //logic
    private List<SongsItem> list;
    private Random r;
    private MediaPlayer mPlayer;
    private AlertDialog myAlertDialog;
    private long userTime;
    private int round;
    private int counterForRound;
    private CountDownTimer timer;
    private static final int limit = 7;
    private int count, number;
    private static final String TAG = "MyTag";
    private int turn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        counterForRound = 1;
        super.onCreate(savedInstanceState);
        //only landscape rule
        windowSettings();

        setContentView(R.layout.activity_classic_game);
        //get from intent count of rounds
        Bundle bundle = getIntent().getExtras();
        number = bundle.getInt("round");
        ////////////////////////////////////
        round = 5;
        //initialize data
        init();
        /*Log.d(TAG, "round - " + round);*/

        //filling List of songs
        for(int i = 0; i < new Database().namesOfSongs.length; i++){
            list.add(new SongsItem(new Database().namesOfSongs[i], new Database().songs[i], new Database().authors[i]));
        }
        Collections.shuffle(list);



        newQuestion(turn);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnText1.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){ // check if name of song equals it database element
                    Toast.makeText(classic_game.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < round){
                        clickCorrectSettings();
                    }
                    else{
                        clickFinishSettings();

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
                if (btnText2.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    Toast.makeText(classic_game.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < round){
                        clickCorrectSettings();

                    }
                    else{
                        clickFinishSettings();

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
                if (btnText3.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    Toast.makeText(classic_game.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < round){
                        clickCorrectSettings();

                    }
                    else{
                        clickFinishSettings();

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
                if (btnText4.getText().toString().equalsIgnoreCase(list.get(turn-1).getName())){
                    Toast.makeText(classic_game.this, "correct", Toast.LENGTH_SHORT).show();
                    if(turn < round){
                        clickCorrectSettings();

                    }
                    else{
                        clickFinishSettings();


                    }
                }
                else{
                    clickFalseSettings();

                }
            }
        });

    }
    private void init(){
        r = new Random();
        //texts on ImageButton
        btnText1 = findViewById(R.id.btntext1);
        btnText2 = findViewById(R.id.btntext2);
        btnText3 = findViewById(R.id.btntext3);
        btnText4 = findViewById(R.id.btntext4);
        /////////////////////////////////////
        // ImageButtons
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        ////////////////////////////////////
        //header values
        //current number of round
        rd = findViewById(R.id.rd);
        //score counter
        counter = findViewById(R.id.result);
        //last time
        timerView = findViewById(R.id.timerView);
        //list of DB songs
        list = new ArrayList<>();
    }
    private void clickCorrectSettings(){ // new question starting

        turn++;
        count++;
        mPlayer.stop();
        newQuestion(turn);
        startTimer();
    }
    private void clickFalseSettings(){ //new question starting
        mPlayer.stop();
        Toast.makeText(classic_game.this, "wrong", Toast.LENGTH_SHORT).show();
        if (count >=0) count--;

        turn++;
        newQuestion(turn);
    }
    private void clickFinishSettings(){ // game finishing
        mPlayer.stop();
        showDialog(classic_game.this);
        Toast.makeText(classic_game.this, "finish", Toast.LENGTH_SHORT).show();
    }

    private void windowSettings(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void newQuestion(int number){
        //запуск песни
        int reference = list.get(number - 1).getRef();
        String txt = "ваш результат - " + count;
        counter.setText(txt);
        startTimer();
        String text = "раунд " + counterForRound +  "/ " + round;
        rd.setText(text);
        //make music player
        mPlayer= MediaPlayer.create(this, reference);
        mPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //start music
                mPlayer.setLooping(true);
                mPlayer.start();
            }
        });
        //inserting answers to button
        int correct_song = r.nextInt(4) + 1;

        int firstButton = number - 1;
        int secondButton;
        int thirdButton;
        int fourthButton;

        switch (correct_song){
            case 1:
                btnText1.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText2.setText(list.get(secondButton).getName());
                btnText3.setText(list.get(thirdButton).getName());
                btnText4.setText(list.get(fourthButton).getName());
                break;
            case 2:
                btnText2.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText1.setText(list.get(secondButton).getName());
                btnText3.setText(list.get(thirdButton).getName());
                btnText4.setText(list.get(fourthButton).getName());
                break;
            case 3:
                btnText3.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText2.setText(list.get(secondButton).getName());
                btnText1.setText(list.get(thirdButton).getName());
                btnText4.setText(list.get(fourthButton).getName());
                break;
            case 4:
                btnText4.setText(list.get(firstButton).getName());

                do {
                    secondButton = r.nextInt(list.size());
                }while (secondButton == firstButton);
                do {
                    thirdButton = r.nextInt(list.size());
                }while (thirdButton == firstButton || thirdButton == secondButton);
                do {
                    fourthButton = r.nextInt(list.size());
                }while (fourthButton == firstButton || fourthButton == secondButton || fourthButton == thirdButton);
                btnText2.setText(list.get(secondButton).getName());
                btnText3.setText(list.get(thirdButton).getName());
                btnText1.setText(list.get(fourthButton).getName());
                break;
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        mPlayer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
    }

    public void showDialog(Context context) {//Dialog if game end
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
    public void showPromIfTrueDialog(Context context){ // dialog if answer is correct
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("верно")
                .setMessage("это была песня ......" +
                        "продолжаем?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("да", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Закрываем окно
                        dialog.dismiss();
                        finish();

                    }
                });
        builder.show();
    }
    public void showPromIfFalseDialog(Context context){ // dialog if answer is incorrect
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("неверно")
                .setMessage("это была песня ......" +
                        "продолжаем?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("да", new DialogInterface.OnClickListener() {
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
            timer = new CountDownTimer(5000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    String times = "осталось до конца раунда - " + millisUntilFinished / 1000;
                    timerView.setText(times);
                }

                @Override
                public void onFinish() {

                    mPlayer.stop();
                    count--;
                    if (turn < round) {

                        turn++;
                        counterForRound++;
                        newQuestion(turn);
                    }
                    else{
                        mPlayer.stop();
                        showDialog(classic_game.this);
                        Toast.makeText(classic_game.this, "finish", Toast.LENGTH_SHORT).show();

                    }
                }
            }.start();
        }
        else{
            timer.cancel();
            timer = new CountDownTimer(5000, 1000) {
                @SuppressLint("SetTextI18n")
                @Override
                public void onTick(long millisUntilFinished) {
                    timerView.setText("осталось до конца раунда - " + millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {

                    mPlayer.stop();
                    if (count > -1){
                        count--;
                    }
                    if (turn < round) {

                        turn++;
                        counterForRound++;
                        newQuestion(turn);
                    }

                }
            }.start();
        }

    }
}