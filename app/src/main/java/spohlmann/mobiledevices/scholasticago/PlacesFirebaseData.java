package spohlmann.mobiledevices.scholasticago;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spohlmann on 5/7/2017.
 * Create the connection to the Firebase and sends the data to the Firebase to be stored
 */

public class PlacesFirebaseData {
    DatabaseReference myPlacesDbRef;
    FirebaseAuth firebaseAuth;
    public static final String PlacesDataTag = "Places Data";
    private String userId;

    /*
     * Opens the connection to the database
     */
    public DatabaseReference open()  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myPlacesDbRef = database.getReference(PlacesDataTag);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null) {
            userId = user.getUid();
        }

        return myPlacesDbRef;
    }

    /*
     * Closes the connection to the database
     */
    public void close() {

    }

    /*
     * This passes in the the object and overwrites it in the database
     * used for updating the complete String
     */
    public Places updatePlace(Places newPlace) {
        myPlacesDbRef.child("users").child(userId).child(newPlace.getKey()).setValue(newPlace);
        return newPlace;
    }

    /*
     * Creates the object and stores them in the database under the user
     * @param the necessary fields for the Places object
     */
    public Places createPlace(String locationName, String completed,
                              Double locationLatitude, Double locationLongitude, Integer code) {
        String key = myPlacesDbRef.child(PlacesDataTag).push().getKey();
        Places newPlace = new Places(key, locationName, completed,
                locationLatitude, locationLongitude, code);
        myPlacesDbRef.child("users").child(userId).child(key).setValue(newPlace);
        return newPlace;
    }

    /*
     * Sets up an ArrayList<> that contains all of the Places objects in it.
     * Adds the places based on who is logged in. 
     */
    public List<Places> getAllPlaces(DataSnapshot dataSnapshot) {
        List<Places> placesList = new ArrayList<Places>();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null)
            for (DataSnapshot data : dataSnapshot.child("users").child(userId).getChildren()) {
                Places places = data.getValue(Places.class);
                placesList.add(places);
            }
        return placesList;
    }
}
