package com.jdkgroup.interviewdemo.pagination;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubApi {

    @GET("/users")
    Flowable<Response<List<User>>> getUser(@Query("since") int since, @Query("per_page") int perPage);
}
