package olis.getsyproximity.library;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by GTO on 03.08.2014.
 */
public class Utils {
    public static void validateUserLoginParameters(String userId, String loginType, String instanceToken) throws IllegalArgumentException {
        if(userId == null || userId.equals("")) {
            throw new IllegalArgumentException(Constants.BAD_USER_ID);
        }
        else if(loginType == null || !loginType.equals(GetsyProximityClient.LOGIN_TYPE_EMAIL) && !loginType.equals(GetsyProximityClient.LOGIN_TYPE_PHONE)) {
            throw new IllegalArgumentException(Constants.BAD_LOGIN_TYPE);
        }
        else if(instanceToken == null || instanceToken.equals("")) {
            throw new IllegalArgumentException(Constants.BAD_INSTANCE_TOKEN);
        }
    }

    public static void validateInitializeSDKParameters (int appId, String appToken) throws IllegalArgumentException {
        if(appId <= 0) {
            throw new IllegalArgumentException(Constants.BAD_APP_ID);
        }
        else if(appToken == null || appToken.equals("")) {
            throw new IllegalArgumentException(Constants.BAD_APP_TOKEN);
        }
    }

    public static void validateGetExperiencesParameters (String instanceToken) throws IllegalArgumentException {
        if(instanceToken == null || instanceToken.equals("")) {
            throw new IllegalArgumentException(Constants.BAD_INSTANCE_TOKEN);
        }
    }

    public static void validateSyncUserParameters (String firstName, String lastName, String gender, String birthday, String instanceToken) {
        if(firstName == null || firstName.equals("")) {
            throw new IllegalArgumentException(Constants.BAD_FIRST_NAME);
        }
        else if(lastName == null || lastName.equals("")) {
            throw new IllegalArgumentException(Constants.BAD_LAST_NAME);
        }
        else if(gender == null || !gender.equals(GetsyProximityClient.GENDER_FEMALE) && !gender.equals(GetsyProximityClient.GENDER_MALE)) {
            throw new IllegalArgumentException(Constants.BAD_GENDER);
        }
        else if(instanceToken == null || instanceToken.equals("")){
            throw new IllegalArgumentException(Constants.BAD_INSTANCE_TOKEN);
        }
        else if(birthday == null) {
            throw new IllegalArgumentException(Constants.BAD_BIRTHDAY);
        }
        try {
            new SimpleDateFormat("yyyy-MM-dd").parse(birthday);
        } catch (ParseException e) {
            throw new IllegalArgumentException(Constants.BAD_BIRTHDAY_FORMAT);
        }
    }

}
