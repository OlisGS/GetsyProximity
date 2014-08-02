package olis.getsyproximity.library;

import android.app.Application;

/**
 * Created by GTO on 31.07.2014.
 */
public class GPApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GetsyProximityClient.onApplicationCreate(this);
    }
}
