package olis.getsyproximity.library;

import android.content.Context;
import android.util.Log;

import olis.getsyproximity.R;
import olis.getsyproximity.library.Request.ExperiencesRequest;
import olis.getsyproximity.library.Request.InitializeSDKRequest;
import olis.getsyproximity.library.Request.SyncUserRequest;
import olis.getsyproximity.library.Request.UserLoginRequest;
import olis.getsyproximity.library.Response.CallbackResponse;
import olis.getsyproximity.library.Response.ExperiencesResponse;
import olis.getsyproximity.library.Response.InitializeSDKResponse;
import olis.getsyproximity.library.Response.SyncUserResponse;
import olis.getsyproximity.library.Response.UserLoginResponse;
import olis.getsyproximity.library.RestAPIInterfaces.RestAPIGETInterfaces;
import olis.getsyproximity.library.RestAPIInterfaces.RestAPIPOSTInterfaces;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by OlisG on 29.07.2014.
 */
public class GetsyProximityClient {

    private static String sBaseDevURL;
    private String TAG = "getsyproximityclient";
    public static String sAppToken;
    public static int sAppId;

    public static final String LOGIN_TYPE_EMAIL = "email";
    public static final String LOGIN_TYPE_PHONE = "phone";
    public static final String GENDER_FEMALE = "f";
    public static final String GENDER_MALE = "m";


    private RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(sBaseDevURL)
            .build();


    private static class SingletonHolder {
        private final static GetsyProximityClient singleton = new GetsyProximityClient();
    }

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

    public static synchronized GetsyProximityClient getInstance(){
        return SingletonHolder.singleton;
    }

    public SyncUserResponse syncUser (String firstName, String lastName, String gender, String birthday, String instanceToken){
        SyncUserResponse response;
        try {
            Utils.validateSyncUserParameters(firstName, lastName, gender, birthday, instanceToken);
            NetworkThread networkThread = new NetworkThread(new SyncUserRequest(firstName, lastName, gender, birthday), restAdapter, instanceToken);
            networkThread.sendRequest();
            response = (SyncUserResponse) networkThread.getResponse();
            return response;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getMessage());
            response = new SyncUserResponse();
            response.setStatus(e.getMessage());
            return response;
        }
    }

    public UserLoginResponse userLogin (String userId, String loginType, String instanceToken){
        UserLoginResponse response;
        try {
            Utils.validateUserLoginParameters(userId, loginType, instanceToken);
            NetworkThread networkThread = new NetworkThread(new UserLoginRequest(userId, loginType), restAdapter, instanceToken);
            networkThread.sendRequest();
            response = (UserLoginResponse) networkThread.getResponse();
            return response;
        } catch (IllegalArgumentException e ) {
            Log.e(TAG, e.getMessage());
            response = new UserLoginResponse();
            response.setStatus(e.getMessage());
            return response;
        }
    }
    
    public InitializeSDKResponse initializeSDK (int appId, String appToken){
        InitializeSDKResponse response;
        try {
            Utils.validateInitializeSDKParameters(appId, appToken);
            NetworkThread networkThread = new NetworkThread(new InitializeSDKRequest(appId, appToken), restAdapter);
            networkThread.sendRequest();
            response = (InitializeSDKResponse) networkThread.getResponse();
            return response;
        } catch (IllegalArgumentException e ) {
            Log.e(TAG, e.getMessage());
            response = new InitializeSDKResponse();
            response.setStatus(e.getMessage());
            return response;
        }
    }

    public ExperiencesResponse getExperiences(String instanceToken){
        ExperiencesResponse response;
        try {
            Utils.validateGetExperiencesParameters(instanceToken);
            NetworkThread networkThread = new NetworkThread(new ExperiencesRequest(), restAdapter, instanceToken);
            networkThread.sendRequest();
            response = (ExperiencesResponse) networkThread.getResponse();
            return response;
        } catch (IllegalArgumentException e) {
            Log.e(TAG, e.getMessage());
            response = new ExperiencesResponse();
            response.setStatus(e.getMessage());
            return response;
        }
    }

    public void asyncUserLogin(String userId, String loginType, String instanceToken, final CallbackResponse<UserLoginResponse> response) {
        try {
            Utils.validateUserLoginParameters(userId, loginType, instanceToken);
            RestAPIPOSTInterfaces.AsyncUserLoginInterface userLoginInterface = restAdapter.create(RestAPIPOSTInterfaces.AsyncUserLoginInterface.class);
            userLoginInterface.userLogin(instanceToken, new UserLoginRequest(userId, loginType), new Callback<UserLoginResponse>() {
                @Override
                public void success(UserLoginResponse res, Response rawResponse) {
                    response.callback(res);
                }

                @Override
                public void failure(RetrofitError error) {
                    UserLoginResponse res = new UserLoginResponse();
                    res.setStatus(error.getMessage());
                    response.callback(res);
                }
            });
        } catch (IllegalArgumentException e) {
            UserLoginResponse res = new UserLoginResponse();
            res.setStatus(e.getMessage());
            response.callback(res);
            Log.e(TAG, e.getMessage());
        }
    }

    public void asyncInitializeSDK(int appId, String appToken, final CallbackResponse<InitializeSDKResponse> response) {
        try {
            Utils.validateInitializeSDKParameters(appId, appToken);
            RestAPIPOSTInterfaces.AsyncInitializeSDKInterface initializeInterface = restAdapter.create(RestAPIPOSTInterfaces.AsyncInitializeSDKInterface.class);
            initializeInterface.initialize(new InitializeSDKRequest(appId, appToken), new Callback<InitializeSDKResponse>() {
                @Override
                public void success(InitializeSDKResponse res, retrofit.client.Response rawResponse) {
                    response.callback(res);
                }

                @Override
                public void failure(RetrofitError error) {
                    InitializeSDKResponse emptyResponse = new InitializeSDKResponse();
                    emptyResponse.setStatus(error.getMessage());
                    response.callback(emptyResponse);
                }
            });
        } catch (IllegalArgumentException e ) {
            InitializeSDKResponse emptyResponse = new InitializeSDKResponse();
            emptyResponse.setStatus(e.getMessage());
            response.callback(emptyResponse);
            Log.e(TAG, e.getMessage());
        }
    }

    public void asyncGetExperiences(String instanceToken, final CallbackResponse<ExperiencesResponse> response) {
        try {
            Utils.validateGetExperiencesParameters(instanceToken);
            RestAPIGETInterfaces.AsyncGetExperiences getExperiencesInterface = restAdapter.create(RestAPIGETInterfaces.AsyncGetExperiences.class);
            getExperiencesInterface.getExperiences(instanceToken, new Callback<ExperiencesResponse>() {
                @Override
                public void success(ExperiencesResponse res, Response rawResponse) {
                    response.callback(res);
                }

                @Override
                public void failure(RetrofitError error) {
                    ExperiencesResponse res = new ExperiencesResponse();
                    res.setStatus(error.getMessage());
                    response.callback(res);
                }
            });
        } catch (IllegalArgumentException e ) {
            ExperiencesResponse res = new ExperiencesResponse();
            res.setStatus(e.getMessage());
            response.callback(res);
            Log.e(TAG, e.getMessage());
        }
    }

    public void asyncSyncUser(String firstName, String lastName, String gender, String birthday, String instanceToken, final CallbackResponse<SyncUserResponse> response) {
        try {
            Utils.validateSyncUserParameters(firstName, lastName, gender, birthday, instanceToken);
            RestAPIGETInterfaces.AsyncSyncUser asyncSyncUser = restAdapter.create(RestAPIGETInterfaces.AsyncSyncUser.class);
            asyncSyncUser.syncUser(instanceToken, new SyncUserRequest(firstName, lastName, gender, birthday), new Callback<SyncUserResponse>() {
                @Override
                public void success(SyncUserResponse res, Response rawResponse) {
                    response.callback(res);
                }

                @Override
                public void failure(RetrofitError error) {
                    SyncUserResponse res = new SyncUserResponse();
                    res.setStatus(error.getMessage());
                    response.callback(res);
                }
            });
        } catch (IllegalArgumentException e ) {
            SyncUserResponse res = new SyncUserResponse();
            res.setStatus(e.getMessage());
            response.callback(res);
            Log.e(TAG, e.getMessage());
        }
    }

    public static void onApplicationCreate(Context ctx){
        sBaseDevURL = ctx.getResources().getString(R.string.base_dev_URL);
    }
}
