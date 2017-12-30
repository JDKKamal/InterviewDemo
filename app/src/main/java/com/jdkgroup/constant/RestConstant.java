package com.jdkgroup.constant;

public interface RestConstant {

    public final String BASE_URL = "http://alldealzz-uk.herokuapp.com/";
    public final String IMAGE_URL = "";

    public final String API_GETCATEGORY = "api/v6/deals/148";

    public final int REQUEST_AUTH = 1;
    public final int REQUEST_NO_AUTH = 0;

    /* DB STATUS */
    public final int TYPE_DELETION = 0;
    public final int TYPE_INSERTION = 1;
    public final int TYPE_MODIFICATION = 2;

    /* ERROR CODE */
    public int ERROR_SERVICE_UNAVAILABLE = 503;
    public int ERROR_INTERNAL_SERVER = 500;
    public int ERROR_BAD_Gateway = 502;
    public int ERROR_NOT_FOUND = 404;
    public int ERROR_Forbidden = 403;
    public int ERROR_PRECONDITION_FAILED = 403;
    public int ERROR_OK = 200;
    public int ERROR_No_Content = 204;
    public int ERROR_Method_Not_Allowed = 405;
    public int ERROR_RESPONSE_ERROR = 400;
}
