package com.jdkgroup.connection;

import android.content.Context;

import com.google.gson.GsonBuilder;
import com.jdkgroup.baseclass.BaseApplication;
import com.jdkgroup.constant.RestConstant;
import com.jdkgroup.utils.AppUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient implements RestConstant {

    private static RestClient instance = null;

    private RestService restService;

    private HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE);
    File cacheFile = new File(BaseApplication.getBaseApplication().getCacheDir(), "cache");
    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb

    public static RestClient getInstance(Context context, int request) {
        if (instance == null) {
            instance = new RestClient(context, request);
        }
        return instance;
    }

    public RestClient(Context context, int request) {
        Retrofit retrofit;
        if (request == REQUEST_AUTH) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    //.addConverterFactory(new ToStringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClientAuth)
                    .build();
            restService = retrofit.create(RestService.class);
        } else if (request == REQUEST_NO_AUTH) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(new ToStringConverterFactory())
                    //.addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build();
            restService = retrofit.create(RestService.class);
        }
    }

    public RestService getService() {
        return restService;
    }

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging).build();

    OkHttpClient httpClientAuth = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .cache(cache)
            .addInterceptor(chain -> {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder().header("Authorization", "bearer ac65df43b1a76c8672f3f4da2c282f822a7bf39c40b47de7af930dc21110f0f4");
                requestBuilder.header("Accept", "application/json");
                requestBuilder.method(original.method(), original.body());
                Request request = requestBuilder.build();
                Response response = chain.proceed(request);

                AppUtils.logD("API Call " + response + "");

                if (response.isSuccessful()) {
                    String data = response.body().string();
                    return response.newBuilder().body(ResponseBody.create(response.body().contentType(), data)).build();
                }
                return response;

            })
            .build();
}