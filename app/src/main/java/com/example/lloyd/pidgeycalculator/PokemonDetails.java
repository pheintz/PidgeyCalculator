package com.example.lloyd.pidgeycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PokemonDetails extends AppCompatActivity{

    Pokemon previousPokemon;
    Pokemon foundPokemon;
    Pokemon nextPokemon;

    PokemonData pokeDex;
    MoveStats moveStats;
    AttackMove tempAttack;

    List<AttackMove> pokemonAttacks;
    List<Pokemon> pokemonList;

    TextView pname;
    TextView type;
    TextView weight;
    TextView maxCP;
    TextView candyToEvolve;
    TextView tempTextView;

    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);
        Intent i = getIntent();
        String name = i.getExtras().getString("pokemonName");
        pokemonList = new ArrayList<>();
        pokemonAttacks = new ArrayList<>();
        pokeDex = new PokemonData();
        moveStats = new MoveStats();

        this.pokemonList = pokeDex.getPokemonList();
        Iterator<Pokemon> itr = this.pokemonList.iterator();
        foundPokemon = itr.next();

        if(!name.equals(foundPokemon.name)){
            while(!name.equals(foundPokemon.name)) {
                previousPokemon = foundPokemon;
                foundPokemon = itr.next();
            }
        }

        if(itr.hasNext()){
            nextPokemon = itr.next();
        }
        if(name.equals(foundPokemon.name)) {
            setPokemonAttacks();
        }

        gestureDetector = new GestureDetector(new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                if (gestureDetector.onTouchEvent(event)) {
                    return true;
                }
                return false;
            }
        };

        RelativeLayout screen = (RelativeLayout) findViewById(R.id.fullScreen);
        screen.setOnTouchListener(gestureListener);

        LinearLayout layout1 = (LinearLayout) findViewById(R.id.linearLayout);
        layout1.setOnTouchListener(gestureListener);

        LinearLayout layout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        layout2.setOnTouchListener(gestureListener);

        ScrollView layout3 = (ScrollView) findViewById(R.id.scrollView);
        layout3.setOnTouchListener(gestureListener);

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
                PokemonDetails.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PDexMenu:
                myIntent = new Intent(this, PokemonListView.class);
                PokemonDetails.this.startActivity(myIntent);
                finish();
                return true;
            case R.id.PCalcMenu:
                myIntent = new Intent(this, PidgeyCalculator.class);
                PokemonDetails.this.startActivity(myIntent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    void setPokemonAttacks(){
        /**
         *  Get info about attacks:
         *  myAttacks [0-1] are primary attacks
         *  myAttacks[2-4] are special attacks
         */

        AttackMove tempAttack;
        pokemonAttacks = moveStats.getMoveList();
        Iterator<AttackMove> aItr = pokemonAttacks.iterator();
        tempAttack = aItr.next();
        while(aItr.hasNext()){
            if(tempAttack.moveName.equals(foundPokemon.mainAttack1)){

                tempTextView = (TextView) findViewById(R.id.AttackName1);
                tempTextView.setText(foundPokemon.mainAttack1);

                tempTextView = (TextView) findViewById(R.id.AttackType1);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) findViewById(R.id.DPS1);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) findViewById(R.id.Damage1);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            }

            else if(tempAttack.moveName.equals(foundPokemon.mainAttack2)){

                tempTextView = (TextView) findViewById(R.id.AttackName2);
                tempTextView.setText(foundPokemon.mainAttack2);

                tempTextView = (TextView) findViewById(R.id.AttackType2);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) findViewById(R.id.DPS2);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) findViewById(R.id.Damage2);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            }

            else if(tempAttack.moveName.equals(foundPokemon.subAttack1)){
                tempTextView = (TextView) findViewById(R.id.SpecialName1);
                tempTextView.setText(foundPokemon.subAttack1);

                tempTextView = (TextView) findViewById(R.id.SpecialType1);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) findViewById(R.id.SpecialDPS1);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) findViewById(R.id.SpecialDamage1);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            }

            else if(tempAttack.moveName.equals(foundPokemon.subAttack2)){

                tempTextView = (TextView) findViewById(R.id.SpecialName2);
                tempTextView.setText(foundPokemon.subAttack2);

                tempTextView = (TextView) findViewById(R.id.SpecialType2);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) findViewById(R.id.SpecialDPS2);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) findViewById(R.id.SpecialDamage2);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            }
            else if(tempAttack.moveName.equals(foundPokemon.subAttack3)){
                tempTextView = (TextView) findViewById(R.id.SpecialName3);
                tempTextView.setText(foundPokemon.subAttack3);

                tempTextView = (TextView) findViewById(R.id.SpecialType3);
                tempTextView.setText("Type: " + tempAttack.getType());

                tempTextView = (TextView) findViewById(R.id.SpecialDPS3);
                tempTextView.setText("DPS: " + tempAttack.getDps());

                tempTextView = (TextView) findViewById(R.id.SpecialDamage3);
                tempTextView.setText("Damage: " + tempAttack.getDamage());
            }
            tempAttack = aItr.next();
        }

        /** set text fields for pokemon info */
        pname = (TextView) findViewById(R.id.PokemonName);
        pname.setText(foundPokemon.name);

        type = (TextView) findViewById(R.id.PokemonType);
        type.setText("Type(s): " + foundPokemon.type );
        if(!foundPokemon.subtype.equals("")){
            type.setText(type.getText() + " / " + foundPokemon.subtype);
        }

        weight = (TextView) findViewById(R.id.PokemonWeight);
        weight.setText("Weight: " + foundPokemon.weight);

        maxCP = (TextView) findViewById(R.id.PokemonMaxCP);
        maxCP.setText("Max CP: " + foundPokemon.maxCP);

        candyToEvolve = (TextView) findViewById(R.id.PokemonEvoCost);
        candyToEvolve.setText("Evo Cost: " + foundPokemon.candyToEvolve);
    }

    // Gesture Detect
    private static final int SWIPE_MIN_DISTANCE = 30;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 50;
    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH) {
                    return false;
                }

                // right to left swipe
                if (e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    //previous pokemon
                    if(nextPokemon == null){
                        Iterator <Pokemon> itr = pokemonList.iterator();
                        nextPokemon = itr.next();
                    }
                    try{
                        Intent myIntent = new Intent(PokemonDetails.this, PokemonDetails.class);
                        //Putting the Id of image as an extra in intent
                        myIntent.putExtra("pokemonName", nextPokemon.name);
                        PokemonDetails.this.startActivity(myIntent);
                        PokemonDetails.this.overridePendingTransition(R.anim.right_to_left_exit,
                                R.anim.right_to_left_enter);
                        finish();
                        return true;
                    }
                    catch(Exception e){
                        return false;
                    }
                //// Logic
                }

                else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY)
                {
                    if(previousPokemon == null) {
                        previousPokemon = pokemonList.get(pokemonList.size() - 1);
                    }
                    try{
                        Intent myIntent = new Intent(PokemonDetails.this, PokemonDetails.class);
                        //Putting the Id of image as an extra in intent
                        myIntent.putExtra("pokemonName", previousPokemon.name);
                        PokemonDetails.this.startActivity(myIntent);
                        PokemonDetails.this.overridePendingTransition(R.anim.left_to_right_enter,
                                R.anim.left_to_right_exit);
                        finish();
                        return true;
                    }
                    catch(Exception e) {
                        return false;
                    }
                }
            } catch (Exception e) {
            // nothing
            }
            return false;
        }
        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
            // TODO Auto-generated method stub
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }

}


