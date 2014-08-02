package olis.getsyproximity.library.Request;

/**
 * Created by GTO on 02.08.2014.
 */
public class BasicRequest {
    final String userId;
    final String loginType;
    final String appId;
    final String appToken;
    public BasicRequest(){
        this.userId = null;
        this.loginType = null;
        this.appId = null;
        this.appToken = null;
    }
    public BasicRequest(String userId) {
        this.userId = userId;
        this.loginType = null;
        this.appId = null;
        this.appToken = null;
    }
    public BasicRequest(String userId, String loginType) {
        this.userId = userId;
        this.loginType = loginType;
        this.appId = null;
        this.appToken = null;
    }
    public BasicRequest(String userId, String loginType, String appId) {
        this.userId = userId;
        this.loginType = loginType;
        this.appId = appId;
        this.appToken = null;
    }
    public BasicRequest(String userId, String loginType, String appId, String appToken){
        this.userId = userId;
        this.loginType = loginType;
        this.appId = appId;
        this.appToken = appToken;
    }
}
