package edu.temple.munz.responsivefragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment {

    ListView listView; //we got the reference to this listview in onCreateView from the parent activity
    Context parent; //the parent activity, which is Context (it's also a GetPlanetInterface object-
                    // sometimes we'll have to cast it as that,
                    //could also define this as a GetPlanetInterface then cast it as Context when needed)


    public MasterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //can explicitly prevent it from attaching unless it had implemented the interface using this if check
        //if(!(context instanceof GetPlanetInterface))
        this.parent = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_master, container, false);
        //get reference to the listView
        listView = v.findViewById(R.id.listView);

        listView.setAdapter(new ArrayAdapter<>(parent, android.R.layout.simple_list_item_1, parent.getResources().getStringArray(R.array.planets)));

        //onClickListener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String planetName = (String)adapterView.getItemAtPosition(position);
                ((GetPlanetInterface) parent).planetSelected(planetName);
            }
        });



        return v;
    }


    //this interface doesn't know that it's an image, it just knows it's getting info on the item-
    // this is a fairly generic interface, could be even more generic (could be getItemInterface, with method itemSelected)
    interface GetPlanetInterface {
        void planetSelected(String planetName);
    }

}
