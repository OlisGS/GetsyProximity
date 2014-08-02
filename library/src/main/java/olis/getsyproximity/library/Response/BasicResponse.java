package olis.getsyproximity.library.Response;

import olis.getsyproximity.library.Constants;

/**
 * Created by GTO on 01.08.2014.
 */
public class BasicResponse {
    private String serverTimestamp;
    private String status;
    private String instanceToken;
    private User user;

    protected void setStatus(String status) {
        this.status = status;
    }
    protected String getInstanceToken(){
        return instanceToken;
    }

    protected String getServerTimestamp(){
        return serverTimestamp;
    }

    protected String getStatus(){
        return status;
    }
    protected User getUser(){
        return user;
    }

    protected class User {
        private String firstName;
        private String lastName;
    }
    public boolean isOK(){
        if(status.equals(Constants.STATUS_OK)){
            return true;
        }else{return false;}
    }
}
