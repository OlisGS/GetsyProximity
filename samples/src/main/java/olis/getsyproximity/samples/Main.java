package olis.getsyproximity.samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import olis.getsyproximity.library.Server;


public class Main extends Activity implements View.OnClickListener{
    private Button mInitialize;
    private Server mServer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mInitialize = (Button)findViewById(R.id.initialize);
        mInitialize.setOnClickListener(this);
        mServer = new Server();
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
        switch (v.getId()){
            case R.id.initialize:
                Server.InitResponse response = mServer.initializeSDK();
                if(response != null){
                    Toast.makeText(getApplicationContext(), response.getStatus(), Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Bad ass", Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
