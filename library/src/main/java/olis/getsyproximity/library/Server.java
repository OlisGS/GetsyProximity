package olis.getsyproximity.library;

import android.content.Context;

import olis.getsyproximity.R;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Olis on 29.07.2014.
 */
public class Server {

    private static String sBaseDevURL;
    private static String sAppToken;
    private static String sAppId;

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(sBaseDevURL)
            .build();

    private interface GitHubService {
        @POST("/sdk/initialize")
        void listRepos(@Body InitRequest initRequest, Callback<InitResponse> callback);
    }

    private class InitRequest{
        final String appId;
        final String appToken;
        public InitRequest(String appId, String appToken){
            this.appId = appId;
            this.appToken = appToken;
        }
    }
    public class InitResponse{
        String serverTimestamp;
        String status;
        String instanceToken;

        public String getInstanceToken() {
            return instanceToken;
        }

        public String getServerTimestamp() {
            return serverTimestamp;
        }

        public String getStatus() {
            return status;
        }
    }

    public InitResponse initializeSDK(){
        GitHubService gitHubService = restAdapter.create(GitHubService.class);
        final InitResponse[] response = new InitResponse[1];
        gitHubService.listRepos(new InitRequest(sAppId, sAppToken),new Callback<InitResponse>() {
            @Override
            public void success(InitResponse o, Response res) {
               response[0] = o;
            }

            @Override
            public void failure(RetrofitError error) {
                response[0] = null;
            }
        });
        return initializeSDK();
    }

    public static void onApplicationCreate(Context ctx){
        sBaseDevURL = ctx.getResources().getString(R.string.base_dev_URL);
        sAppToken = ctx.getResources().getString(R.string.app_token);
        sAppId = ctx.getResources().getString(R.string.app_Id);
    }
}
