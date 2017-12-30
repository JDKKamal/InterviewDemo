package com.jdkgroup.interviewdemo.pagination;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GitHubService {

    private GitHubService() {
    }

    public static GitHubApi createGitHubService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://api.github.com");

        return builder.build().create(GitHubApi.class);
    }

}
