package olis.getsyproximity.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import olis.getsyproximity.library.GetsyProximityClient;
import olis.getsyproximity.library.Response.BasicResponse;
import olis.getsyproximity.library.Response.CallbackResponse;
import olis.getsyproximity.library.Response.InitializeSDKResponse;
import olis.getsyproximity.library.Response.UserLoginResponse;


public class Main extends Activity implements View.OnClickListener{
    private Button mInitialize;
    private Button mLogin;
    private Button mInitializeAsync;
    private Button mLoginAsync;
    private GetsyProximityClient mGetsyProximityClient;
    private String userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInitialize = (Button)findViewById(R.id.initialize);
        mLogin = (Button)findViewById(R.id.login);
        mInitializeAsync = (Button)findViewById(R.id.initialize_async);
        mLoginAsync = (Button)findViewById(R.id.login_async);
        initAllClickListeners();
        mGetsyProximityClient = new GetsyProximityClient();
    }

    private void initAllClickListeners(){
        mInitialize.setOnClickListener(this);
        mLogin.setOnClickListener(this);
        mInitializeAsync.setOnClickListener(this);
        mLoginAsync.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        BasicResponse response;
        switch (v.getId()){
            case R.id.initialize_async:
                mGetsyProximityClient.asyncInitializeSDK(new CallbackResponse<InitializeSDKResponse>() {
                    @Override
                    public void callback(InitializeSDKResponse response) {
                        if(response.isOK()){
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                            userId = response.getInstanceToken();
                        }else{
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
                break;
            case R.id.login_async:
                mGetsyProximityClient.asyncUserLogin("olis@gmail.com", GetsyProximityClient.LOGIN_TYPE_EMAIL, new CallbackResponse<UserLoginResponse>() {
                    @Override
                    public void callback(UserLoginResponse response) {
                        if(response.isOK()){
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            case R.id.login:
                response = mGetsyProximityClient.userLogin("olis@gmail.com",GetsyProximityClient.LOGIN_TYPE_EMAIL);
                if(response.isOK()){
                    Toast.makeText(getApplicationContext(), ((UserLoginResponse)response).getStatus(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), ((UserLoginResponse)response).getStatus(), Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.initialize:
                response = mGetsyProximityClient.initializeSDK();
                if(response.isOK()){
                    Toast.makeText(getApplicationContext(), ((InitializeSDKResponse)response).getStatus(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), ((InitializeSDKResponse)response).getStatus(), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
