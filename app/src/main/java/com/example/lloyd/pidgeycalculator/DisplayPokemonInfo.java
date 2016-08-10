package com.example.lloyd.pidgeycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DisplayPokemonInfo extends AppCompatActivity {

    PokemonData pokeDex;
    List<Pokemon> pokemonList;

    TextView pname;
    TextView type;
    TextView subType;
    TextView weight;
    TextView maxCP;
    TextView mainAttack;
    TextView mainAttack2;
    TextView special;
    TextView candyToEvolve;

    ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pokemon_info);

        backgroundImage = (ImageView) findViewById(R.id.imageView);

        Intent i = getIntent();
        String name = i.getExtras().getString("pokemonName");
        pokemonList = new ArrayList<>();
        pokeDex = new PokemonData();

        this.pokemonList = pokeDex.getPokemonList();
        Iterator<Pokemon> itr = this.pokemonList.iterator();
        Pokemon foundPokemon = itr.next();

        while(!name.equals(foundPokemon.name)){
            foundPokemon = itr.next();
        }

        if(foundPokemon.type.equals("Bug")) {
            backgroundImage.setImageResource(R.drawable.bugtype);
        }
        else if(foundPokemon.type.equals("Dark")){
            backgroundImage.setImageResource(R.drawable.darktype);
            }
        else if(foundPokemon.type.equals("Dragon")){
            backgroundImage.setImageResource(R.drawable.dragontype);
            }
        else if(foundPokemon.type.equals("Electric")){
            backgroundImage.setImageResource(R.drawable.electrictype);
            }
        else if(foundPokemon.type.equals("Fairy")){
            backgroundImage.setImageResource(R.drawable.fairytype);
            }
        else if(foundPokemon.type.equals("Fighting")){
            backgroundImage.setImageResource(R.drawable.fightingtype);
            }
        else if(foundPokemon.type.equals("Fire")){
            backgroundImage.setImageResource(R.drawable.firetype);
            }
        else if(foundPokemon.type.equals("Flying")){
            backgroundImage.setImageResource(R.drawable.flyingtype);
            }
        else if(foundPokemon.type.equals("Ghost")){
            backgroundImage.setImageResource(R.drawable.ghosttype);
            }
        else if(foundPokemon.type.equals("Grass")){
            backgroundImage.setImageResource(R.drawable.grasstype);
            }
        else if(foundPokemon.type.equals("Ground")){
            backgroundImage.setImageResource(R.drawable.groundtype);
            }
        else if(foundPokemon.type.equals("Ice")){
            backgroundImage.setImageResource(R.drawable.icetype);
            }
        else if(foundPokemon.type.equals("Normal")){
            backgroundImage.setImageResource(R.drawable.normaltype);
            }
        else if(foundPokemon.type.equals("Poison")){
            backgroundImage.setImageResource(R.drawable.poisontype);
            }
        else if(foundPokemon.type.equals("Psychic")){
            backgroundImage.setImageResource(R.drawable.psychictype);
            }
        else if(foundPokemon.type.equals("Rock")){
            backgroundImage.setImageResource(R.drawable.rocktype);
            }
        else if(foundPokemon.type.equals("Steel")){
            backgroundImage.setImageResource(R.drawable.steeltype);
            }
        else if(foundPokemon.type.equals("Water")){
            backgroundImage.setImageResource(R.drawable.watertype);
            }


        if(name.equals(foundPokemon.name)) {

            /** set text fields for pokemon info */
            pname = (TextView) findViewById(R.id.PokemonHeader);
            pname.setText(foundPokemon.name);

            type = (TextView) findViewById(R.id.Type1);
            type.setText(foundPokemon.type);

            subType = (TextView) findViewById(R.id.Type2);
            subType.setText(foundPokemon.subtype);

            weight = (TextView) findViewById(R.id.Weight);
            weight.setText(foundPokemon.weight);

            maxCP = (TextView) findViewById(R.id.MaxCP);
            maxCP.setText(foundPokemon.maxCP);

            mainAttack = (TextView) findViewById(R.id.MainAttack1);
            mainAttack.setText(foundPokemon.mainAttack1);

            mainAttack2 = (TextView) findViewById(R.id.MainAttack2);
            mainAttack2.setText(foundPokemon.mainAttack2);

            special = (TextView) findViewById(R.id.Special1);
            special.setText(foundPokemon.subAttack1);

            special = (TextView) findViewById(R.id.Special2);
            special.setText(foundPokemon.subAttack2);

            special = (TextView) findViewById(R.id.Special3);
            special.setText(foundPokemon.subAttack3);

            candyToEvolve = (TextView) findViewById(R.id.CandyToEvo);
            candyToEvolve.setText(foundPokemon.candyToEvolve);
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
                DisplayPokemonInfo.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PDexMenu:
                myIntent = new Intent(this, PokemonListView.class);
                DisplayPokemonInfo.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PCalcMenu:
                myIntent = new Intent(this, PokemonListView.class);
                DisplayPokemonInfo.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
