package com.chenqiao.controlclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnExit = findViewById(R.id.btn_exit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.stop();
                System.exit(0);
            }
        });


        Button btn_1 = findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.go();
            }
        });
        Button btn_2 = findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.back();
            }
        });

        Button btn_3 = findViewById(R.id.btn_3);
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.left();
            }
        });
        Button btn_4 = findViewById(R.id.btn_4);
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.right();
            }
        });
        Button btn_5 = findViewById(R.id.btn_5);
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.stop();
            }
        });

        Button btn_6 = findViewById(R.id.btn_6);
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.pivot_left();
            }
        });

        Button btn_7 = findViewById(R.id.btn_7);
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.pivot_right();
            }
        });

        Button btn_8 = findViewById(R.id.btn_8);
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.p_left();
            }
        });

        Button btn_9 = findViewById(R.id.btn_9);
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.p_right();
            }
        });


        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ControlActivity.class));
            }
        });
    }

}
