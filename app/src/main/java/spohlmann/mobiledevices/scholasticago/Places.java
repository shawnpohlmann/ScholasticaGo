package spohlmann.mobiledevices.scholasticago;

import java.io.Serializable;

/**
 * Created by spohlmann on 5/7/2017.
 */

public class Places implements Serializable{
    private String key;
    private String locationName;
    private String complete;
    private Double locationLatitude;
    private Double locationLongitude;

    public Places() {

    }

    public Places(String key, String locationName, String complete, Double locationLatitude,
                  Double locationLongitude) {
        this.key = key;
        this.locationName = locationName;
        this.complete = complete;
        this.locationLatitude = locationLatitude;
        this.locationLongitude = locationLongitude;
    }

    public String getKey() {return key;}
    public void setKey() {this.key = key;}

    public String getLocationName() {return locationName;}
    public void setLocationName() {this.locationName = locationName;}

    public String getComplete() {return complete;}
    public void setComplete() {this.complete = complete;}

    public Double getLocationLatitude() {return locationLatitude;}
    public void setLocationLatitude() { this.locationLatitude = locationLatitude;}

    public Double getLocationLongitude() {return locationLongitude;}
    public void setLocationLongitude() {this.locationLongitude = locationLongitude;}

    public String toString() {
        return "Place{" +
                "Name='" + locationName + '\'' +
                ", Complete='" + complete + '\'' +
                '}';
    }




}
