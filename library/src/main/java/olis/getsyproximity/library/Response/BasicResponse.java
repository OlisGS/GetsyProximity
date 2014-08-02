package olis.getsyproximity.library.Response;

import java.util.List;

import olis.getsyproximity.library.Constants;

/**
 * Created by OlisG on 01.08.2014.
 */
public class BasicResponse {
    private String serverTimestamp;
    private String status;
    private String instanceToken;
    private User user;
    private List<ExperienceObject> experiencesList;

    protected String getInstanceToken(){
        return instanceToken;
    }

    protected String getServerTimestamp(){
        return serverTimestamp;
    }

    protected String getStatus(){
        return status;
    }

    public List<ExperienceObject> getExperiencesList() {
        return experiencesList;
    }

    public void setExperiencesList(List<ExperienceObject> experiencesList) {
        this.experiencesList = experiencesList;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    public void setInstanceToken(String instanceToken) {
        this.instanceToken = instanceToken;
    }

    public void setServerTimestamp(String serverTimestamp) {
        this.serverTimestamp = serverTimestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }

    protected User getUser(){
        return user;
    }

    protected static class ExperienceObject{
        private int id;
        private String behaviour;
        private Notification notification;
        private Content content;
        private int availableNext;

        protected Content getContent() {
            return content;
        }

        protected int getAvailableNext() {
            return availableNext;
        }

        protected int getId() {
            return id;
        }

        protected Notification getNotification() {
            return notification;
        }

        protected String getBehaviour() {
            return behaviour;
        }

        protected void setAvailableNext(int availableNext) {
            this.availableNext = availableNext;
        }

        protected void setBehaviour(String behaviour) {
            this.behaviour = behaviour;
        }

        protected void setContent(Content content) {
            this.content = content;
        }

        protected void setId(int id) {
            this.id = id;
        }

        protected void setNotification(Notification notification) {
            this.notification = notification;
        }
    }

    protected static  class Content {
        private String title;
        private int titleColor;
        private String text;
        private int textColor;
        private String img;
        private int bgColor;
        private String font;

        protected String getText() {
            return text;
        }

        protected int getBgColor() {
            return bgColor;
        }

        protected int getTextColor() {
            return textColor;
        }

        protected int getTitleColor() {
            return titleColor;
        }

        protected String getFont() {
            return font;
        }

        protected String getImg() {
            return img;
        }

        protected String getTitle() {
            return title;
        }

        protected void setText(String text) {
            this.text = text;
        }

        protected void setBgColor(int bgColor) {
            this.bgColor = bgColor;
        }

        protected void setFont(String font) {
            this.font = font;
        }

        protected void setImg(String img) {
            this.img = img;
        }

        protected void setTextColor(int textColor) {
            this.textColor = textColor;
        }

        protected void setTitle(String title) {
            this.title = title;
        }

        protected void setTitleColor(int titleColor) {
            this.titleColor = titleColor;
        }
    }

    protected static class Notification {
        private String text;
        private String sound;

        protected String getSound() {
            return sound;
        }

        protected String getText() {
            return text;
        }

        protected void setSound(String sound) {
            this.sound = sound;
        }

        protected void setText(String text) {
            this.text = text;
        }
    }

    protected static class User {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

    public boolean isOK(){
        if(status.equals(Constants.STATUS_OK)){
            return true;
        }else{return false;}
    }
}
