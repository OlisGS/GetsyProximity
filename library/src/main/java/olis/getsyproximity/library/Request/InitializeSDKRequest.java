package olis.getsyproximity.library.Request;

/**
 * Created by OlisG on 02.08.2014.
 */
public class InitializeSDKRequest extends BasicRequest{
    public InitializeSDKRequest(String appId, String appToken) {
        super(null, null, appId, appToken);
    }
}
