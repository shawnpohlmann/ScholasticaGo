package spohlmann.mobiledevices.scholasticago;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by spohlmann on 5/7/2017.
 */

public class PlaceAdapter extends ArrayAdapter<Places> {
    private List<Places> placesList;
    private Context context;
    private int layoutResource;
    public PlaceAdapter(Context context, int resource, List<Places> placesList) {
        super(context, resource, placesList);
        this.context = context;
        this.layoutResource = resource;
        this.placesList = placesList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Places places = placesList.get(position);
        View view;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.places_row_layout, null);

        TextView tvName = (TextView) view.findViewById(R.id.textViewName);
        TextView tvCompleted = (TextView) view.findViewById(R.id.textViewCompleted);
        tvName.setText(places.getLocationName());
        tvCompleted.setText(places.getComplete());

        return view;
    }
}
