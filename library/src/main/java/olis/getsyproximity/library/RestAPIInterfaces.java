package olis.getsyproximity.library;

import olis.getsyproximity.library.Request.BasicRequest;
import olis.getsyproximity.library.Request.InitializeSDKRequest;
import olis.getsyproximity.library.Request.UserLoginRequest;
import olis.getsyproximity.library.Response.InitializeSDKResponse;
import olis.getsyproximity.library.Response.UserLoginResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by GTO on 02.08.2014.
 */
public class RestAPIInterfaces {
    public interface AsyncInitializeSDKInterface {
        @POST("/sdk/initialize")
        void initialize(@Body InitializeSDKRequest initRequest, Callback<InitializeSDKResponse> callback);
    }

    public interface AsyncUserLoginInterface {
        @POST("/sdk/user/login")
        void userLogin(@Body UserLoginRequest userLoginRequest, Callback<UserLoginResponse> callback);
    }

    public interface InitializeSDKInterface {
        @POST("/sdk/initialize")
        InitializeSDKResponse initialize(@Body BasicRequest initRequest);
    }

    public interface UserLoginInterface {
        @POST("/sdk/user/login")
        UserLoginResponse userLogin(@Body BasicRequest userLoginRequest);
    }
}
