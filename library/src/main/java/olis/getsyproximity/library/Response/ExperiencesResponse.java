package olis.getsyproximity.library.Response;

import java.util.List;

/**
 * Created by GTO on 03.08.2014.
 */
public class ExperiencesResponse extends BasicResponse {
    public String getStatus(){
        return super.getStatus();
    }

    public List<ExperienceObject> getExperiencesList() {
        return super.getExperiencesList();
    }

    public void setStatus(String status) {
        super.setStatus(status);
    }

}
