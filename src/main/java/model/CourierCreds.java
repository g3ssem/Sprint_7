package model;

import static utils.Utils.randomString;

public class CourierCreds {
    private String login;
    private String password;


    public CourierCreds (String login, String password){
        this.login = login;
        this.password = password;
    }
    public static CourierCreds credsFromCourier (Courier courier){
        return new CourierCreds(courier.getLogin(), courier.getPassword());
    }
    public static CourierCreds credsFromCourierWithoutLogin (Courier courier){
        return new CourierCreds("", courier.getPassword());
    }
    public static CourierCreds credsFromCourierWithoutPassword (Courier courier){
        return new CourierCreds(courier.getLogin(), "");
    }
    public static CourierCreds credsFromCourierWithoutCreateCourier (){
        return new CourierCreds(randomString(10), randomString(12));
    }
}
