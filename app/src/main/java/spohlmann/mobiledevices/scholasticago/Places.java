package spohlmann.mobiledevices.scholasticago;

import java.io.Serializable;

/**
 * Created by spohlmann on 5/7/2017.
 * Creates the Places object and implements Serializable
 */

public class Places implements Serializable{
    private String key;
    private String locationName;
    private String complete;
    private Double locationLatitude;
    private Double locationLongitude;
    private Integer code;

    /*
     * Constructor for Places
     */
    public Places() {

    }

    /*
     * Constructor for places that passes in the different values needed.
     */
    public Places(String key, String locationName, String complete, Double locationLatitude,
                  Double locationLongitude, Integer code) {
        this.key = key;
        this.locationName = locationName;
        this.complete = complete;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
        this.code = code;
    }

    /*
     * Gets the Key and returns it
     */
    public String getKey() {return key;}
    /*
     * Sets the key to a new value
     */
    public void setKey(String key) {this.key = key;}

    /*
     * Gets the Name and returns it
     */
    public String getLocationName() {return locationName;}
    /*
     * Sets the Name to a new value
     */
    public void setLocationName(String locationName) {this.locationName = locationName;}

    /*
     * Gets the string saying if it is completed or not and returns it
     */
    public String getComplete() {return complete;}
    /*
     * Sets the Complete String to a new value
     */
    public void setComplete(String complete) {this.complete = complete;}

    /*
     * Gets the Latitude and returns it
     */
    public Double getLocationLatitude() {return locationLatitude;}
    /*
     * Sets the Latitude to a new value
     */
    public void setLocationLatitude(Double locationLatitude) { this.locationLatitude = locationLatitude;}

    /*
     * Gets the Longitude and returns it
     */
    public Double getLocationLongitude() {return locationLongitude;}
    /*
     * Sets the Longitude to a new value
     */
    public void setLocationLongitude(Double locationLongitude) {this.locationLongitude = locationLongitude;}

    /*
     * Gets the Code and returns it
     */
    public int getCode() {return code;}
    /*
     * Sets the Code to a new value
     */
    public void setCode(int code) {this.code = code;}

    /*
     * Displays the information in a String.
     */
    public String toString() {
        return "Place{" +
                "Key='" + key + '\'' +
                "Name='" + locationName + '\'' +
                ", Complete='" + complete + '\'' +
                '}';
    }




}
