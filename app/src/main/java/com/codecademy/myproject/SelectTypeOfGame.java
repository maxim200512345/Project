package com.codecademy.myproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

public class SelectTypeOfGame extends AppCompatActivity {

    private ImageView classicButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        windowSettings();
        setContentView(R.layout.activity_select_type_of_game);
        init();
        listeners();
        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    case 0:
                        makeDialog();


                        break;
                    case 1:
                        Intent intent2 = new Intent(SelectTypeOfGame.this, YesOrNoGame.class);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3 = new Intent(SelectTypeOfGame.this, WhoIsSingerGame.class);
                        startActivity(intent3);
                        break;
                }
            }
        });*/
    }
    private void listeners(){
        classicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeDialog();
            }
        });
    }
    private void windowSettings(){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    private void init(){

        classicButton = findViewById(R.id.classicButton);
    }
    public void makeDialog(){
        LayoutInflater li = LayoutInflater.from(SelectTypeOfGame.this);
        View promptsView = li.inflate(R.layout.activity_alert_dialog, null);
        AlertDialog.Builder mDialogBuilder = new AlertDialog.Builder(SelectTypeOfGame.this);
        mDialogBuilder.setView(promptsView);
        EditText userTime = promptsView.findViewById(R.id.edText);
        mDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getBaseContext(), classic_game.class);
                                intent.putExtra("round", userTime.getText().toString());
                                startActivity(intent);
                            }
                        })
                .setNegativeButton("отмена",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = mDialogBuilder.create();
        alertDialog.show();
    }
}