package edu.temple.munz.responsivefragment;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MasterFragment.GetPlanetInterface {


    DetailFragment df;
    FragmentManager fm;
    boolean singlePane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //figure out whether we're in single-pane or dual-pane mode
        singlePane = findViewById(R.id.container2) == null;
        df = new DetailFragment();


        //create the fragment manager
        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container1, new MasterFragment())
                .commit();


        //show the detail fragment if we're in double pane mode
        if(!singlePane) {
            fm.beginTransaction().replace(R.id.container2, df).addToBackStack(null).commit();
        }
    }


    //implement the method from GetPlanetInterface in MasterFragment.java
    public void planetSelected(String planetName) {

        if(singlePane) {

            DetailFragment newDF = DetailFragment.newInstance(planetName);
            fm.beginTransaction()
                    .replace(singlePane ? R.id.container1 : R.id.container2, newDF)
                    .addToBackStack(null)
                    .commit();
        } else {
            df.changePlanet(planetName);
        }
    }

}
