package olis.getsyproximity.library;

import olis.getsyproximity.library.Request.BasicRequest;
import olis.getsyproximity.library.Request.ExperiencesRequest;
import olis.getsyproximity.library.Request.InitializeSDKRequest;
import olis.getsyproximity.library.Request.SyncUserRequest;
import olis.getsyproximity.library.Request.UserLoginRequest;
import olis.getsyproximity.library.Response.BasicResponse;
import olis.getsyproximity.library.RestAPIInterfaces.RestAPIGETInterfaces;
import olis.getsyproximity.library.RestAPIInterfaces.RestAPIPOSTInterfaces;
import retrofit.RestAdapter;

/**
 * Created by GTO on 02.08.2014.
 */
public class NetworkThread extends Thread{
    private BasicResponse basicResponse;
    private BasicRequest request;
    private RestAdapter restAdapter;
    private String instanceToken;
    public NetworkThread (BasicRequest request, RestAdapter restAdapter, String instanceToken){
        this.request = request;
        this.restAdapter = restAdapter;
        this.instanceToken = instanceToken;
    }
    public NetworkThread (BasicRequest request, RestAdapter restAdapter) {
        this.request = request;
        this.restAdapter = restAdapter;
        this.instanceToken = null;
    }
    @Override
    public void run() {
        if(request instanceof InitializeSDKRequest) {
            RestAPIPOSTInterfaces.InitializeSDKInterface initializeInterface = restAdapter.create(RestAPIPOSTInterfaces.InitializeSDKInterface.class);
            basicResponse = initializeInterface.initialize(request);
        }
        else if(request instanceof UserLoginRequest) {
            RestAPIPOSTInterfaces.UserLoginInterface userLoginInterface = restAdapter.create(RestAPIPOSTInterfaces.UserLoginInterface.class);
            basicResponse = userLoginInterface.userLogin(instanceToken, request);//EOF
        }
        else if(request instanceof ExperiencesRequest) {
            RestAPIGETInterfaces.GetExperiences getExperiencesInterface = restAdapter.create(RestAPIGETInterfaces.GetExperiences.class);
            basicResponse = getExperiencesInterface.getExperiences(instanceToken);
        }
        else if(request instanceof SyncUserRequest) {
            RestAPIGETInterfaces.SyncUser syncUser = restAdapter.create(RestAPIGETInterfaces.SyncUser.class);
            basicResponse = syncUser.syncUser(instanceToken, request);
        }
    }
    public void sendRequest() {
        this.start();
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public BasicResponse getResponse(){
        return basicResponse;
    }
}
