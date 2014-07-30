package olis.getsyproximity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by Olis on 29.07.2014.
 */
public class main extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initializeSDK();
    }

    RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://api.proximity.dev2.getsy.co:40900")
            .build();

    public interface GitHubService {
        @POST("/sdk/initialize")
        void listRepos(@Body InitRequest initRequest, Callback<Object> callback);
    }

    private class InitRequest{
        final String appId;
        final String appToken;
        public InitRequest(String appId, String appToken){
            this.appId = appId;
            this.appToken = appToken;
        }
    }

    public void initializeSDK(){
        GitHubService gitHubService = restAdapter.create(GitHubService.class);
        gitHubService.listRepos(new InitRequest("28","MansPUTqhrvHEzdFmj85S3cVXODZ7Q"),new Callback<Object>() {
            @Override
            public void success(Object o, Response response) {
                Toast.makeText(getApplicationContext(), "ggg3", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getApplicationContext(), "ggg3", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
