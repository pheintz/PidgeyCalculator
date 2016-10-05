package com.example.lloyd.pidgeycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Filter;
import android.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class PokemonListView extends AppCompatActivity implements SearchView.OnQueryTextListener {

    PokemonData myPokedex;
    ListView pListView;
    List<String> pNames;
    List<Pokemon> pokemonList;
    SearchView searchBar;
    ArrayAdapter<String> pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        myPokedex = new PokemonData();
        pNames = new ArrayList<>();

        searchBar = new SearchView(this);
        searchBar = (SearchView) findViewById(R.id.pokemonSearch);
        if (searchBar != null) {
            searchBar.setOnQueryTextListener(this);
        }

        /** Populate List */
        this.pokemonList = myPokedex.getPokemonList();
        Iterator<Pokemon> itr = this.pokemonList.iterator();
        while(itr.hasNext()){
            pNames.add(itr.next().name);
        }
        pListView = (ListView) findViewById(R.id.pList);

        /** Set Adapter for searchbar and listview */
        pAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pNames);
        pListView.setAdapter(pAdapter);
        pListView.setTextFilterEnabled(true);
        setupSearchView();

        pListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                int itemPosition = position;

                //Get the String value of the item where the user clicked
                String itemValue = (String) pListView.getItemAtPosition(position);

                //In order to start displaying new activity we need an intent
                Intent intent = new Intent(getApplicationContext(), PokemonDetailsWithFragments.class);

                //Putting the Id of image as an extra in intent
                intent.putExtra("pokemonName", itemValue);

                //Here we will pass the previously created intent as parameter
                startActivity(intent);
            }
        });
     }

    private void setupSearchView() {
        searchBar.setIconifiedByDefault(false);
        searchBar.setOnQueryTextListener(this);
        searchBar.setSubmitButtonEnabled(true);
        searchBar.setQueryHint("Search Here");
    }

    @Override
    public boolean onQueryTextChange(String newText)
    {
        Filter filter = pAdapter.getFilter();
        if (TextUtils.isEmpty(newText)) {
            pAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pNames);
            pListView.setAdapter(pAdapter);
        } else {
            filter.filter(newText);
        }
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /** Send back to home screen android back button pressed */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK ) {
            Intent myIntent = new Intent(this, HomeScreen.class);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PokemonListView.this.startActivity(myIntent);
            finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.EvoMenu:
                myIntent = new Intent(this, EvolutionCalc.class);
                PokemonListView.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PCalcMenu:
                myIntent = new Intent(this, PidgeyCalculator.class);
                PokemonListView.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}