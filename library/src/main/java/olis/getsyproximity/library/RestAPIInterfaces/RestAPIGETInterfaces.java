package olis.getsyproximity.library.RestAPIInterfaces;

import olis.getsyproximity.library.Request.BasicRequest;
import olis.getsyproximity.library.Response.ExperiencesResponse;
import olis.getsyproximity.library.Response.SyncUserResponse;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by GTO on 03.08.2014.
 */
public class RestAPIGETInterfaces {
    public interface AsyncGetExperiences {
        @GET("/sdk/experiences")
        void getExperiences(@Header("X-Application-Token") String xApplicationTokenHeader, Callback<ExperiencesResponse> callback);
    }

    public interface GetExperiences {
        @GET("/sdk/experiences")
        ExperiencesResponse getExperiences(@Header("X-Application-Token") String xApplicationTokenHeader);
    }

    public interface AsyncSyncUser {
        @GET("/sdk/user/sync")
        void syncUser(@Header("X-Application-Token") String xApplicationTokenHeader, @Body BasicRequest syncUserRequest, Callback<SyncUserResponse> callback);
    }

    public interface SyncUser {
        @GET("/sdk/user/sync")
        SyncUserResponse syncUser(@Header("X-Application-Token") String xApplicationTokenHeader, @Body BasicRequest syncUserRequest);
    }

}
