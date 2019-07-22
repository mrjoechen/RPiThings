package com.chenqiao.controlclient;

import android.widget.Toast;

import com.chenqiao.controlclient.http.RestRxClient;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ControlUtils {

    static void go() {
        request(CONTROL_ACTION.GO);
    }

    static void back() {
        request(CONTROL_ACTION.BACK);
    }

    static void left() {
        request(CONTROL_ACTION.LEFT);
    }

    static void right() {
        request(CONTROL_ACTION.RIGHT);
    }


    /**
     * 后左转弯
     */
    static void pivot_left(){
        request(CONTROL_ACTION.PIVOT_LEFT);
    }

    /**
     * 后右转弯
     */
    static void pivot_right(){
        request(CONTROL_ACTION.PIVOT_RIGHT);
    }

    /**
     * 原地左转
     */
    static void p_left(){
        request(CONTROL_ACTION.P_LEFT);
    }

    /**
     * 原地右转
     */
    static void p_right(){
        request(CONTROL_ACTION.P_RIGHT);
    }

    static void stop() {
        request(CONTROL_ACTION.STOP);
    }

    static void request(CONTROL_ACTION action){

        String url = "http://192.168.31.25:8234/"+action.name();
//        Observable<String> observable = RestRxCreator.getRestRxService().get("http://www.baidu.com", null);
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(@NonNull String s) {
//                        Toast.makeText(getApplication(), "action.name()", Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        RestRxClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {
//                        Toast.makeText(App.getInstance(), "test....", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
