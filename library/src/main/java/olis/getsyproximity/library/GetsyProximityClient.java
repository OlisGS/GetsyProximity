package olis.getsyproximity.library;

import android.content.Context;
import android.util.Log;

import olis.getsyproximity.R;
import olis.getsyproximity.library.Request.InitializeSDKRequest;
import olis.getsyproximity.library.Request.UserLoginRequest;
import olis.getsyproximity.library.Response.CallbackResponse;
import olis.getsyproximity.library.Response.InitializeSDKResponse;
import olis.getsyproximity.library.Response.UserLoginResponse;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Olis on 29.07.2014.
 */
public class GetsyProximityClient {

    private static String sBaseDevURL;
    private static String sAppToken;
    private static String sAppId;
    private String TAG = "getsyproximity.library.ServerResponse";
    private String mInstanceToken = null;

    public static final String LOGIN_TYPE_EMAIL = "email";
    public static final String LOGIN_TYPE_PHONE = "phone";

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(sBaseDevURL)
            .build();


    
    private void addXApplicationTokenHeader(final String instanceToken){
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("X-Application-Token", instanceToken);
            }
        };
        restAdapter = new RestAdapter.Builder()
                .setEndpoint(sBaseDevURL)
                .setRequestInterceptor(requestInterceptor)
                .build();
    }

    public UserLoginResponse userLogin(String userId, String loginType) {
        if(mInstanceToken != null){
            NetworkThread networkThread = new NetworkThread(new UserLoginRequest(userId, loginType), restAdapter);
            networkThread.sendRequest();
            UserLoginResponse response = (UserLoginResponse) networkThread.getResponse();
            return response;
        } else {
            UserLoginResponse emptyResponse = new UserLoginResponse();
            emptyResponse.setStatus(Constants.BAD_INSTANCE_TOKEN);
            return emptyResponse;
        }
    }
    
    public InitializeSDKResponse initializeSDK() {
        NetworkThread networkThread = new NetworkThread(new InitializeSDKRequest(sAppId, sAppToken), restAdapter);
        networkThread.sendRequest();
        InitializeSDKResponse response = (InitializeSDKResponse) networkThread.getResponse();
        mInstanceToken = response.getInstanceToken();
        addXApplicationTokenHeader(mInstanceToken);
        return response;
    }

    public void asyncUserLogin(String userId, String emailOrPhoneNumber, final CallbackResponse response) {
        RestAPIInterfaces.AsyncUserLoginInterface userLoginInterface = restAdapter.create(RestAPIInterfaces.AsyncUserLoginInterface.class);
        userLoginInterface.userLogin(new UserLoginRequest(userId, emailOrPhoneNumber), new Callback<UserLoginResponse>() {
            @Override
            public void success(UserLoginResponse res, Response rawResponse) {
                try {
                    response.callback(res);
                } catch (ClassCastException e) {
                    UserLoginResponse emptyResponse = new UserLoginResponse();
                    res.setStatus(e.getMessage());
                    response.callback(emptyResponse);
                    Log.e(TAG,e.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                UserLoginResponse res = new UserLoginResponse();
                res.setStatus(error.getMessage());
                response.callback(res);
            }
        });
    }

    public void asyncInitializeSDK(final CallbackResponse response) {
        RestAPIInterfaces.AsyncInitializeSDKInterface initializeInterface = restAdapter.create(RestAPIInterfaces.AsyncInitializeSDKInterface.class);
        initializeInterface.initialize(new InitializeSDKRequest(sAppId, sAppToken), new Callback<InitializeSDKResponse>() {
            @Override
            public void success(InitializeSDKResponse res, retrofit.client.Response rawResponse) {
                try {
                    mInstanceToken = res.getInstanceToken();
                    addXApplicationTokenHeader(mInstanceToken);
                    response.callback(res);
                } catch (ClassCastException e) {
                    InitializeSDKResponse emptyResponse = new InitializeSDKResponse();
                    res.setStatus(e.getMessage());
                    response.callback(emptyResponse);
                    Log.e(TAG,e.getMessage());
                }
            }

            @Override
            public void failure(RetrofitError error) {
                InitializeSDKResponse emptyResponse = new InitializeSDKResponse();
                emptyResponse.setStatus(error.getMessage());
                response.callback(emptyResponse);
            }
        });
    }

    public static void onApplicationCreate(Context ctx){
        sBaseDevURL = ctx.getResources().getString(R.string.base_dev_URL);
        sAppToken = ctx.getResources().getString(R.string.app_token);
        sAppId = ctx.getResources().getString(R.string.app_Id);
    }
}
