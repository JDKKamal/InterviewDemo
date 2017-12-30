package com.jdkgroup.connection;

import com.jdkgroup.model.callapi.ModelCallApi;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface RestService {

    @FormUrlEncoded
    @POST
    Observable<ModelCallApi> apiPost(@Url String url, @FieldMap HashMap<String, String> params);

    @POST
    Observable<String> apiPost(@Url String url);

    @FormUrlEncoded
    @PUT
    Observable<String> apiPut(@Url String url, @FieldMap HashMap<String, String> params);

    @FormUrlEncoded
    @HTTP(method = "DELETE", hasBody = true)
    Observable<String> apiDelete(@Url String url, @FieldMap HashMap<String, String> params);

    @DELETE
    Observable<String> apiDeleteMember(@Url String url, @Body String params);

    @Multipart
    @POST
    Observable<String> apiPostWithUpload(@Url String url, @PartMap HashMap<String, RequestBody> params, @Part MultipartBody.Part body);

    @FormUrlEncoded
    @PUT
    Observable<String> apiupdateSettingsPut(@Url String url, @FieldMap HashMap<String, Object> params);

    @GET
    Observable<String> getLocationNameFromLatLng(@Url String url, @QueryMap Map<String, String> options);

    @GET
    Observable<String> apiGet(@Url String url);
}


