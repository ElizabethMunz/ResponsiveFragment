package edu.temple.munz.responsivefragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    ImageView imageView;
    String planetName = ""; //this is grabbed from the onCreate method
    public static final String PLANET_KEY = "planet_name"; // key for the bundle (used in newInstance method)

    public DetailFragment() {
        // Required empty public constructor
    }


    //factory method that creates instance of the fragment
    public static DetailFragment newInstance(String planetName) {
        DetailFragment df = new DetailFragment();
        Bundle b = new Bundle();
        b.putString(PLANET_KEY, planetName);
        df.setArguments(b);
        return df;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) { //prevent null pointer exception with this if
            planetName = getArguments().getString(PLANET_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detail, container, false);
        imageView = v.findViewById(R.id.imageView);

        change(planetName);

        return v;
    }

    public void changePlanet(String planetName) {
        change(planetName);
    }

    private void change(String planetName) {
        //get the correct image (using the Picasso library to make it easier b/c images are frustrating)
        //to add the library: right-click on app folder on left, Open Module Settings, (+) to add new library, search for Picasso
        //      Picasso.get() //get an instance of Picasso
        //             .load(R.drawable.earth) //find our desired image
        //             .into(imageView);         //put it into the imageView

        switch(planetName) {
            case "Earth": Picasso.get().load(R.drawable.earth).into(imageView); break;
            case "Mars": Picasso.get().load(R.drawable.mars).into(imageView); break;
            case "Venus": Picasso.get().load(R.drawable.venus).into(imageView); break;
            default: Picasso.get().load(R.drawable.circle).into(imageView); break;
        }

    }

}
