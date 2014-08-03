package olis.getsyproximity.samples;

import android.app.Application;

import olis.getsyproximity.library.GetsyProximityClient;

/**
 * Created by GTO on 03.08.2014.
 */
public class SampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Main.sAppId = this.getResources().getInteger(R.integer.app_Id);
        Main.sAppToken = this.getResources().getString(R.string.app_token);
        GetsyProximityClient.onApplicationCreate(this);
    }
}
