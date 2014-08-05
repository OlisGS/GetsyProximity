package olis.getsyproximity.library.Request;

/**
 * Created by GTO on 03.08.2014.
 */
public class SyncUserRequest extends BasicRequest {
    public SyncUserRequest (String firstName, String lastName, String gender, String birthday){
        super(null, null, 0, null, firstName, lastName, gender, birthday);
    }
    public String getFirstName(){
        return super.firstName;
    }
}
