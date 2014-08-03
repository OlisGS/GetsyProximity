package olis.getsyproximity.library.Request;

/**
 * Created by GTO on 02.08.2014.
 */
public class BasicRequest {
    protected String userId;
    protected String loginType;

    protected int appId;
    protected String appToken;

    protected String firstName;
    protected String lastName;
    protected String gender;
    protected String birthday;

    protected BasicRequest(){
    }
    protected BasicRequest(String userId, String loginType) {
        this.userId = userId;
        this.loginType = loginType;
    }
    protected BasicRequest(String userId, String loginType, int appId, String appToken){
        this.userId = userId;
        this.loginType = loginType;
        this.appId = appId;
        this.appToken = appToken;
    }
    protected BasicRequest(String userId, String loginType, int appId, String appToken, String firstName, String lastName, String gender, String birthday) {
        this.userId = userId;
        this.loginType = loginType;
        this.appId = appId;
        this.appToken = appToken;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthday = birthday;
    }
}
