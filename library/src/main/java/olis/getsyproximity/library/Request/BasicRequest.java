package olis.getsyproximity.library.Request;

import java.lang.reflect.Array;
import java.util.ArrayList;

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

    protected ArrayList<Event> eventsList = new ArrayList<Event>();

    protected class Event {
        private int timestamp;
        private String type;
        private Data data;
        private Description description;
        protected Event (int timestamp, String type, Data data, Description description)

        protected int getTimestamp() {
            return timestamp;
        }

        protected String getType() {
            return type;
        }

        protected void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }

        protected void setType(String tupe) {
            this.type = tupe;
        }
    }

    protected class Description {
        private String notified;
        private String swiped;
        private String displayed;
        private String entered;
        private String exited;
        protected Description (String notified, String swiped, String displayed) {
            this.notified = notified;
            this.swiped = swiped;
            this.displayed = displayed;
        }
        protected Description (String entered, String exited) {
            this.entered = entered;
            this.exited = exited;
        }
        protected class BeaconDescription extends Description {
            protected BeaconDescription(String entered, String exited) {
                super(entered, exited);
            }
        }
        protected class ExperienceDescription extends Description{
            protected ExperienceDescription(String notified, String swiped, String displayed) {
                super(notified, swiped, displayed);
            }
        }
    }

    protected class Data {
        private int id;
        private int uuid;
        private int major;
        private int minor;
        private int rssi;
        private String distance;

        protected Data (int id) {
            this.id = id;
        }

        protected Data (int uuid, int major, int minor, int rssi, String distance) {
            this.uuid = uuid;
            this.major = major;
            this.minor = minor;
            this.rssi = rssi;
            this.distance = distance;
        }

        protected class BeaconData extends Data {
            protected BeaconData(int uuid, int major, int minor, int rssi, String distance) {
                super(uuid, major, minor, rssi, distance);
            }
        }

        protected class ExperienceData extends Data {
            protected ExperienceData(int id) {
                super(id);
            }
        }
    }

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
