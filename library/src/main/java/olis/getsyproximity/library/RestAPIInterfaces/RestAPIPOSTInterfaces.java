package olis.getsyproximity.library.RestAPIInterfaces;

import java.io.EOFException;

import olis.getsyproximity.library.Request.BasicRequest;
import olis.getsyproximity.library.Response.InitializeSDKResponse;
import olis.getsyproximity.library.Response.UserLoginResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by GTO on 02.08.2014.
 */
public class RestAPIPOSTInterfaces {
    public interface AsyncInitializeSDKInterface {
        @POST("/sdk/initialize")
        void initialize(@Body BasicRequest initRequest, Callback<InitializeSDKResponse> callback);
    }

    public interface AsyncUserLoginInterface {
        @POST("/sdk/user/login")
        void userLogin(@Header("X-Application-Token") String xApplicationTokenHeader, @Body BasicRequest userLoginRequest, Callback<UserLoginResponse> callback);
    }

    public interface InitializeSDKInterface {
        @POST("/sdk/initialize")
        InitializeSDKResponse initialize(@Body BasicRequest initRequest);
    }

    public interface UserLoginInterface {
        @POST("/sdk/user/login")
        UserLoginResponse userLogin(@Header("X-Application-Token") String xApplicationTokenHeader, @Body BasicRequest userLoginRequest) throws EOFException;
    }
}
