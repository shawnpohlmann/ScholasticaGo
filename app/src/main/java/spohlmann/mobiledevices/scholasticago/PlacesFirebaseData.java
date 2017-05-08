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
 */

public class PlacesFirebaseData {
    DatabaseReference myPlacesDbRef;
    FirebaseAuth firebaseAuth;
    public static final String PlacesDataTag = "Places Data";
    private String userId;


    public DatabaseReference open()  {
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myPlacesDbRef = database.getReference(PlacesDataTag);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        return myPlacesDbRef;
    }

    public void close() {

    }

    public Places createPlace(String locationName, String completed,
                              Double locationLatitude, Double locationLongitude) {
        String key = myPlacesDbRef.child(PlacesDataTag).push().getKey();
        Places newPlace = new Places(key, locationName, completed,
                locationLatitude, locationLongitude);
        myPlacesDbRef.child("users").child(userId).child(key).setValue(newPlace);
        return newPlace;
    }
    public List<Places> getAllPlaces(DataSnapshot dataSnapshot) {
        List<Places> placesList = new ArrayList<Places>();
        for (DataSnapshot data : dataSnapshot.child("users").child(userId).getChildren()) {
            Places places = data.getValue(Places.class);
            placesList.add(places);
        }
        return placesList;
    }
}
