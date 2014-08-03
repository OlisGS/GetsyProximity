package olis.getsyproximity.samples;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import olis.getsyproximity.library.GetsyProximityClient;
import olis.getsyproximity.library.Response.BasicResponse;
import olis.getsyproximity.library.Response.CallbackResponse;
import olis.getsyproximity.library.Response.ExperiencesResponse;
import olis.getsyproximity.library.Response.InitializeSDKResponse;
import olis.getsyproximity.library.Response.UserLoginResponse;
/**
 * Created by OlisG on 02.08.2014.
 */

public class Main extends Activity implements View.OnClickListener{
    private Button mInitialize;
    private Button mLogin;
    private Button mInitializeAsync;
    private Button mLoginAsync;
    private Button mGetExperiences;
    private Button mGetExperiencesAsync;
    private Button mSyncUser;
    private Button mSyncUserAsync;
    private GetsyProximityClient mGetsyProximityClient;
    private String userId = "olis@gmail.com";
    private String mInstanceToken;
    public static String sAppToken;
    public static int sAppId;
    private final int DIALOG = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInitialize = (Button)findViewById(R.id.initialize);
        mLogin = (Button)findViewById(R.id.login);
        mInitializeAsync = (Button)findViewById(R.id.initialize_async);
        mLoginAsync = (Button)findViewById(R.id.login_async);
        mGetExperiences = (Button)findViewById(R.id.get_experiences);
        mGetExperiencesAsync = (Button)findViewById(R.id.get_experiences_async);
        mSyncUser = (Button) findViewById(R.id.sync_user);
        mSyncUserAsync = (Button) findViewById(R.id.sync_user_async);
        initAllClickListeners();
        mGetsyProximityClient = new GetsyProximityClient();
    }

    private void initAllClickListeners(){
        mInitialize.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mInitializeAsync.setOnClickListener(this);
        mLoginAsync.setOnClickListener(this);
        mGetExperiences.setOnClickListener(this);
        mGetExperiencesAsync.setOnClickListener(this);
        mSyncUserAsync.setOnClickListener(this);
        mSyncUser.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
 private class AsyncS extends AsyncTask<Void, Void ,Void>{
     BasicResponse resp;
     public AsyncS (BasicResponse resp){
         this.resp = resp;
     }

     @Override
     protected Void doInBackground(Void... params) {
         resp = mGetsyProximityClient.initializeSDK(sAppId, sAppToken);
         return null;
     }

     @Override
     protected void onPostExecute(Void aVoid) {
         if(resp.isOK()){
             mInstanceToken = ((InitializeSDKResponse)resp).getInstanceToken();
             Toast.makeText(getApplicationContext(), ((InitializeSDKResponse)resp).getStatus(), Toast.LENGTH_LONG).show();
         }else{
             Toast.makeText(getApplicationContext(), ((InitializeSDKResponse)resp).getStatus(), Toast.LENGTH_LONG).show();
         }
         super.onPostExecute(aVoid);
     }
 }
    @Override
    public void onClick(View v) {
        BasicResponse basicResponse;
        switch (v.getId()){
            case R.id.initialize_async:
                mGetsyProximityClient.asyncInitializeSDK(sAppId, sAppToken, new CallbackResponse<InitializeSDKResponse>() {
                    @Override
                    public void callback(InitializeSDKResponse response) {
                        if(response.isOK()){
                            mInstanceToken = response.getInstanceToken();
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.login_async:
                mGetsyProximityClient.asyncUserLogin(userId, GetsyProximityClient.LOGIN_TYPE_EMAIL, mInstanceToken, new CallbackResponse<UserLoginResponse>() {
                    @Override
                    public void callback(UserLoginResponse response) {
                        if(response.isOK()){
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.login:
                basicResponse = mGetsyProximityClient.userLogin(userId, GetsyProximityClient.LOGIN_TYPE_EMAIL, mInstanceToken);
                if(basicResponse.isOK()){
                    Toast.makeText(getApplicationContext(), ((UserLoginResponse)basicResponse).getStatus(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), ((UserLoginResponse)basicResponse).getStatus(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.initialize:
                basicResponse = mGetsyProximityClient.initializeSDK(sAppId, sAppToken);
                mInstanceToken = ((InitializeSDKResponse)basicResponse).getInstanceToken();
                break;
            case R.id.get_experiences_async:
                 mGetsyProximityClient.asyncGetExperiences(mInstanceToken, new CallbackResponse<ExperiencesResponse>() {
                    @Override
                    public void callback(ExperiencesResponse response) {
                        if(response.isOK()){
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.get_experiences:
                basicResponse = mGetsyProximityClient.getExperiences(mInstanceToken);
                if(basicResponse.isOK()){
                    Toast.makeText(getApplicationContext(), ((ExperiencesResponse)basicResponse).getStatus(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), ((ExperiencesResponse)basicResponse).getStatus(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.sync_user:
                Dialog dialog = new Dialog();
                dialog.show(getFragmentManager(), "ds");
                break;
            case R.id.sync_user_async:
                Dialog dialog1 = new Dialog();
                dialog1.show(getFragmentManager(), "ds");
                break;
        }
    }

}
