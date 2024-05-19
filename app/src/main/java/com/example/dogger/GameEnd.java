package com.example.dogger;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GameEnd extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameend);
        //configureResetNextButton();
        //configureExitNextButton();
        Button resetButton=findViewById(R.id.resetButton);
        resetButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.resetButton:
                startActivity(new Intent(GameEnd.this,MainActivity.class));
                break;
        }

    }
    /*private void  configureResetNextButton(){
        Button resetButton=(Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameEnd.this,MainActivity.class));
            }
        });
    }
    private void  configureExitNextButton(){
        Button resetButton=(Button) findViewById(R.id.exitButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }*/
}
