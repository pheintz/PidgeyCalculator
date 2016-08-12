package com.example.lloyd.pidgeycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class HomeScreen extends AppCompatActivity implements View.OnClickListener{
    int pidgeys;
    int candies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);


        //Buttons
        Button pokedexbutton = (Button) findViewById(R.id.PokedexButton);
        Button pidgeycalcbutton = (Button) findViewById(R.id.PidgeyCalcButton);
        Button evocalcbutton = (Button) findViewById(R.id.EvoCalcButton);



        //Listeners
        pokedexbutton.setOnClickListener(this);  //register a callback when view is clicked
        pidgeycalcbutton.setOnClickListener(this);
        evocalcbutton.setOnClickListener(this);


    }

    public void onClick (View view){


        if(view.getId() == R.id.PidgeyCalcButton)
        {
            Intent myIntent = new Intent(this, PidgeyCalculator.class);
            HomeScreen.this.startActivity(myIntent);
        }
        else if(view.getId() == R.id.PokedexButton){
            Intent myIntent = new Intent(this, PokemonListView.class);
            HomeScreen.this.startActivity(myIntent);
        }
        else if(view.getId() == R.id.EvoCalcButton){
            Intent myIntent = new Intent(this, EvolutionCalc.class);
            HomeScreen.this.startActivity(myIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.EvoMenu:
                myIntent = new Intent(this, EvolutionCalc.class);
                HomeScreen.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PDexMenu:
                myIntent = new Intent(this, PokemonListView.class);
                HomeScreen.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PCalcMenu:
                myIntent = new Intent(this, PidgeyCalculator.class);
                HomeScreen.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
