package systmorder.apporder;

import java.lang.ref.SoftReference;

/**
 * Created by mansoull on 19/5/2017.
 */

public class UserList {

    private String userEmail;
    private String userID;
    private String userPass;
    private String userName;
    private String userType;
    private String userRestaurantID;
    private String userRestaurantName;

    public UserList(){

    }

    public UserList (String userEmail,String userID, String userPass, String userName, String userType, String userRestaurantID, String userRestaurantName){

        this.userEmail = userEmail;
        this.userID = userID;
        this.userPass = userPass;
        this.userName = userName;
        this.userType = userType;
        this.userRestaurantID = userRestaurantID;
        this.userRestaurantName = userRestaurantName;

    }

    public String getUserEmail(){
        return userEmail;
    }

    public void setUserEmail(String userEmail){
        this.userEmail = userEmail;
    }

    public String getUserID(){
        return userID;
    }

    public void setUserID (String userID){
        this.userID = userID;
    }

    public String getUserPass(){
        return  userPass;
    }

    public void setUserPass(String userPass){
        this.userPass = userPass;
    }

    public String getUserName(){
        return  userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserType(){
        return userType;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getUserRestaurantID() {
        return userRestaurantID;
    }

    public void setUserRestaurantID(String userRestaurantID) {
        this.userRestaurantID = userRestaurantID;
    }

    public String getUserRestaurantName() {
        return userRestaurantName;
    }

    public void setUserRestaurantName(String userRestaurantName) {
        this.userRestaurantName = userRestaurantName;
    }
}
