package com.codecademy.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton btnStart, btnExit, btnStats;
    Animation scaleUp, scaleDown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main);
        btnStart = (ImageButton) findViewById(R.id.buttonStart);
        scaleUp = AnimationUtils.loadAnimation(this, R.anim.scale_up);
        scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);

        btnStart.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                btnStart.startAnimation(scaleUp);
                Intent intent = new Intent(MainActivity.this , SelectTypeOfGame.class);
                startActivity(intent);
            }else if(event.getAction() == MotionEvent.ACTION_UP){
                btnStart.startAnimation(scaleDown);
            }
            return true;
        });
        btnStats.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Statistics.class);
            startActivity(intent);
        });

        /*btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this , SelectTypeOfGame.class);
                startActivity(intent);
            }
        });*/
    }
}