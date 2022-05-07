package com.codecademy.myproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class YesOrNoGame extends AppCompatActivity {
    private Button yes, no;
    private TextView question;
    private List<YesOrNoItem> items;
    private DatabaseYesOrNo db;
    private YesOrNoItem yesOrNoItem;
    private Random random;
    private int turn = 1;
    private int round;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_yes_or_no_game);
        init();
        round = 5;
        makingList();
        Collections.shuffle(items);
        newQuestion(turn);
        listeners();

    }
    private void makingList(){
        for (int i = 0; i < db.answers.length; i++) {
            items.add(new YesOrNoItem(db.answers[i], db.questions[i]));
        }
        Collections.shuffle(items);
    }
    private void listeners()
    {
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yes.getText().toString().equalsIgnoreCase(items.get(turn-1).getAnswer()))
                {
                    if (turn < round) {
                        turn++;
                        newQuestion(turn);
                    }
                    else{
                        showDialog(YesOrNoGame.this);
                    }
                }
                else{

                    Toast.makeText(YesOrNoGame.this, "wrong", Toast.LENGTH_SHORT).show();
                    turn++;
                    newQuestion(turn);
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yes.getText().toString().equalsIgnoreCase(items.get(turn-1).getAnswer()))
                {
                    if (turn < round) {
                        turn++;
                        newQuestion(turn);
                    }
                    else{
                        showDialog(YesOrNoGame.this);
                    }
                }
                else{

                    Toast.makeText(YesOrNoGame.this, "wrong", Toast.LENGTH_SHORT).show();
                    turn++;
                    newQuestion(turn);

                }
            }
        });
    }

    private void init() {
        yes = findViewById(R.id.yes);
        no = findViewById(R.id.no);
        question = findViewById(R.id.question);
        items = new ArrayList<>();
        db = new DatabaseYesOrNo();
        random = new Random();
    }

    private void newQuestion(int number) {


        /*counter.setText("ваш результат - " + Integer.toString(count));*/
        /*startTimer();*/
        /*String text = "раунд " + counterForRound +  "/ " + round;
        rd.setText(text);*/


        int correct_song = random.nextInt(4) + 1;

        int firstButton = number - 1;
        int secondButton;
        question.setText(items.get(firstButton).getQuestion());

        switch (correct_song) {
            case 1:
                yes.setText(items.get(firstButton).getAnswer());

                do {
                    secondButton = random.nextInt(items.size());
                } while (secondButton == firstButton);

                no.setText(items.get(secondButton).getAnswer());

                break;
            case 2:
                no.setText(items.get(firstButton).getAnswer());

                do {
                    secondButton = random.nextInt(items.size());
                } while (secondButton == firstButton);

                yes.setText(items.get(secondButton).getAnswer());

                break;

        }
    }
    private void showDialog(Context context){
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
}