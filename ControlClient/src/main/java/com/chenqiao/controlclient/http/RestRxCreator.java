package com.chenqiao.controlclient.http;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by CHENQIAO on 2017/9/8.
 */

public class RestRxCreator {

    private static final String BASE_URL = "http://www.baidu.com";

    public static RestRxService getRestRxService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RestServiceHolder{
        private static final RestRxService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT.create(RestRxService.class);
    }

    public static WeakHashMap<String, Object> getParams(){
        return ParamsHolder.PARAMS;
    }

    private static final class ParamsHolder{
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    private static final class RetrofitHolder{

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(OkHttpClientHolder.OK_HTTP_CLIENT)
                .build();
    }

    private static final class OkHttpClientHolder{

        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder().connectTimeout(TIME_OUT, TimeUnit.SECONDS).build();
    }


}
