package olis.getsyproximity.library.Response;

/**
 * Created by GTO on 01.08.2014.
 */
public class InitializeSDKResponse extends BasicResponse {

    public String getInstanceToken() {
        return super.getInstanceToken();
    }

    public String getServerTimestamp() {
        return super.getServerTimestamp();
    }

    public String getStatus() {
        return super.getStatus();
    }
    public void setStatus(String status) {
        super.setStatus(status);
    }
}
