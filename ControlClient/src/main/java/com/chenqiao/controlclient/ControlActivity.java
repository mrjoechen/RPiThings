package com.chenqiao.controlclient;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.chenqiao.controlclient.view.ControlView;

public class ControlActivity extends Activity {

    private TextView mLogLeft;
    private TextView mLogRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;

        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.flags |= flagTranslucentNavigation;
        window.setAttributes(attributes);
        getWindow().setStatusBarColor(Color.TRANSPARENT);


        mLogLeft = (TextView) findViewById(R.id.log_left);
        mLogRight = (TextView) findViewById(R.id.log_right);

        ControlView rockerViewLeft = (ControlView) findViewById(R.id.rockerView_left);
        if (rockerViewLeft != null) {
            rockerViewLeft.setCallBackMode(ControlView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
            rockerViewLeft.setOnShakeListener(ControlView.DirectionMode.DIRECTION_8, new ControlView.OnShakeListener() {
                @Override
                public void onStart() {
                    mLogLeft.setText(null);
                }

                @Override
                public void direction(ControlView.Direction direction) {
                    mLogLeft.setText("摇动方向 : " + getDirection(direction));
                }

                @Override
                public void onFinish() {
                    mLogLeft.setText(null);
                }
            });
        }

        ControlView rockerViewRight = (ControlView) findViewById(R.id.rockerView_right);
        if (rockerViewRight != null) {
            rockerViewRight.setOnAngleChangeListener(new ControlView.OnAngleChangeListener() {
                @Override
                public void onStart() {
                    mLogRight.setText(null);
                }

                @Override
                public void angle(double angle) {
                    mLogRight.setText("摇动角度 : " + angle);
                }

                @Override
                public void onFinish() {
                    mLogRight.setText(null);
                }
            });
        }




        findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.stop();
                System.exit(0);
            }
        });

        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ControlUtils.stop();
            }
        });
    }

    private String getDirection(ControlView.Direction direction) {
        String message = null;
        switch (direction) {
            case DIRECTION_LEFT:
                message = "左";
                ControlUtils.left();
                break;
            case DIRECTION_RIGHT:
                message = "右";
                ControlUtils.right();
                break;
            case DIRECTION_UP:
                message = "上";
                ControlUtils.go();
                break;
            case DIRECTION_DOWN:
                message = "下";
                ControlUtils.back();
                break;
            case DIRECTION_UP_LEFT:
                message = "左上";
                break;
            case DIRECTION_UP_RIGHT:
                message = "右上";
                break;
            case DIRECTION_DOWN_LEFT:
                message = "左下";
                ControlUtils.pivot_left();
                break;
            case DIRECTION_DOWN_RIGHT:
                message = "右下";
                ControlUtils.pivot_right();
                break;
            default:
                break;
        }
        return message;
    }

}
