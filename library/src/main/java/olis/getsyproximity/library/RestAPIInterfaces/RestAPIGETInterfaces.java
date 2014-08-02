package olis.getsyproximity.library.RestAPIInterfaces;

import olis.getsyproximity.library.Response.ExperiencesResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by GTO on 03.08.2014.
 */
public class RestAPIGETInterfaces {
    public interface AsyncGetExperiences {
        @GET("/sdk/experiences")
        void getExperiences(Callback<ExperiencesResponse> callback);
    }

    public interface GetExperiences {
        @GET("/sdk/experiences")
        ExperiencesResponse getExperiences();
    }

}
